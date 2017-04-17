package org.poi.spring.config.schema;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellUtil;
import org.poi.spring.config.ColumnDefinition;
import org.poi.spring.config.ExcelWorkBookBeandefinition;
import org.poi.spring.constants.PoiConstant;
import org.poi.spring.exception.ExcelException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.ClassUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by oldflame on 2017/4/8.
 */
public class ExcleWorkBookBeanDefinitionParser implements BeanDefinitionParser {
    private static final Logger logger = LoggerFactory.getLogger(ExcleWorkBookBeanDefinitionParser.class);

    private final Class<?> beanClass;

    public ExcleWorkBookBeanDefinitionParser(Class<ExcelWorkBookBeandefinition> beanDefinitionClass) {
        this.beanClass = beanDefinitionClass;
    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        Object source = parserContext.extractSource(element);
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setSource(source);
        beanDefinition.setBeanClass(beanClass);
        beanDefinition.setLazyInit(false);


        //excleName
        if (!element.hasAttribute(PoiConstant.EXCLE_NAME_ATTRIBUTE)) {
            throw new ExcelException("excleworkbook excle-name has empty");
        }
        String excleName = element.getAttribute(PoiConstant.EXCLE_NAME_ATTRIBUTE);
        beanDefinition.getPropertyValues().addPropertyValue("excleName", excleName);

        String dataClassName = element.getAttribute(PoiConstant.DATA_CLASS_ATTRIBUTE);
        Class dataClass = null;
        try {
            dataClass = ClassUtils.forName(dataClassName, null);
        } catch (ClassNotFoundException e) {
            logger.error("excleworkbook data-class was not find excleName=" + excleName);
            ExcelException exception = new ExcelException("excleworkbook data-class was not find excleName=" + excleName);
            exception.initCause(e);
            throw exception;
        }
        beanDefinition.getPropertyValues().addPropertyValue("dataClass", dataClass);

        String id = dataClassName + PoiConstant.CLASS_NAME_SUFFIX;
        beanDefinition.getPropertyValues().addPropertyValue("id", id);

        if (element.hasAttribute(PoiConstant.SHEET_NAME_ATTRIBUTE)) {
            beanDefinition.getPropertyValues().addPropertyValue("sheetName", element.getAttribute(PoiConstant.SHEET_NAME_ATTRIBUTE));
        }
        if (element.hasAttribute(PoiConstant.SHEET_INDEX_ATTRIBUTE)) {
            beanDefinition.getPropertyValues().addPropertyValue("sheetIndex", element.getAttribute(PoiConstant.SHEET_INDEX_ATTRIBUTE));
        }
        if (element.hasAttribute(PoiConstant.DEFAULT_COLUMN_WIDTH_ATTRIBUTE)) {
            beanDefinition.getPropertyValues().addPropertyValue("columnWidth", element.getAttribute(PoiConstant.DEFAULT_COLUMN_WIDTH_ATTRIBUTE));
        }
        //参数信息
        Map<String, Object> defaultProperties = addDefaultProperties(element);
        beanDefinition.getPropertyValues().addPropertyValue("defaultProperties", defaultProperties);

        List<ColumnDefinition> columnDefinitions = parseColumnElements(element, dataClass, excleName);
        beanDefinition.getPropertyValues().addPropertyValue("columnDefinitions", columnDefinitions);
        parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
        return null;
    }

    private Map<String, Object> addDefaultProperties(Element element) {
        Map<String, Object> properties = new HashMap<>();
        if (element.hasAttribute(PoiConstant.DEFAULT_FONT)) {
            addFontProperties(properties, element.getAttribute(PoiConstant.DEFAULT_FONT));
        }
        if (element.hasAttribute(PoiConstant.DEFAULT_ALIGN_ATTRIBUTE)) {
            addAlignProperties(properties, element.getAttribute(PoiConstant.DEFAULT_ALIGN_ATTRIBUTE));
        }
        return properties;
    }

    private void addWrapTextProperties(Map<String, Object> properties, String value) {
        if (PoiConstant.TRUE_VALUE.equals(value)) {
            properties.put(CellUtil.WRAP_TEXT, true);
        }
    }

    private void addFontProperties(Map<String, Object> properties, String value) {
        try {
            properties.put(CellUtil.FONT, Short.valueOf(value));
        } catch (Exception e) {
            throw new ExcelException("excleworkbook font error value=" + value);
        }
    }

    private void addAlignProperties(Map<String, Object> properties, String value) {
        switch (value) {
            case "left":
                properties.put(CellUtil.ALIGNMENT, HorizontalAlignment.LEFT);
                break;
            case "right":
                properties.put(CellUtil.ALIGNMENT, HorizontalAlignment.RIGHT);
                break;
            case "center":
                properties.put(CellUtil.ALIGNMENT, HorizontalAlignment.CENTER);
                break;
            default:
                properties.put(CellUtil.ALIGNMENT, HorizontalAlignment.GENERAL);
                break;
        }
    }

    private List<ColumnDefinition> parseColumnElements(Element element, Class dataClass, String excleName) {
        //查找fieldnames
        Field[] fields = dataClass.getDeclaredFields();
        Set<String> fieldNames = new HashSet<>();
        for (Field field : fields) {
            fieldNames.add(field.getName());
        }

        List<ColumnDefinition> columnDefinitions = new ArrayList<>();
        NodeList nl = element.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (nodeNameEquals(node, PoiConstant.COLUMN_ELEMENT)) {
                columnDefinitions.add(parseColumnElement((Element) node, fieldNames, excleName));
            }
        }
        return columnDefinitions;
    }


    private ColumnDefinition parseColumnElement(Element columnElement, Set<String> fieldNames, String excleName) {
        ColumnDefinition columnDefinition = new ColumnDefinition();
        //excleName
        if (!columnElement.hasAttribute(PoiConstant.COLUMN_NAME_ATTRIBUTE)) {
            throw new ExcelException("excleworkbook column-name has empty excleName=" + excleName);
        }
        String name = columnElement.getAttribute(PoiConstant.COLUMN_NAME_ATTRIBUTE);
        if (hasNotFieldName(fieldNames, name)) {
            throw new ExcelException("excleworkbook column-name has empty excleName=" + excleName + "  name=" + name);
        }
        columnDefinition.setName(name);

        if (!columnElement.hasAttribute(PoiConstant.TITLE_ATTRIBUTE)) {
            throw new ExcelException("excleworkbook title has empty excleName=" + excleName + " column-name=" + name);
        }
        columnDefinition.setTitle(columnElement.getAttribute(PoiConstant.TITLE_ATTRIBUTE));

        if (columnElement.hasAttribute(PoiConstant.REGEX_ATTRIBUTE)) {
            columnDefinition.setRegex(columnElement.getAttribute(PoiConstant.REGEX_ATTRIBUTE));
        }
        if (columnElement.hasAttribute(PoiConstant.REQUIRED_ATTRIBUTE)) {
            columnDefinition.setRequired(PoiConstant.TRUE_VALUE.equals(columnElement.getAttribute(PoiConstant.REQUIRED_ATTRIBUTE)));
        }
        if (columnElement.hasAttribute(PoiConstant.COLUMN_WIDTH_ATTRIBUTE)) {
            try {
                columnDefinition.setColumnWidth(Integer.parseInt(columnElement.getAttribute(PoiConstant.COLUMN_WIDTH_ATTRIBUTE)));
            } catch (Exception e) {
                throw new ExcelException("excleworkbook column-width has error excleName=" + excleName + " column-name=" + name);
            }
        }
        if (columnElement.hasAttribute(PoiConstant.DEFAULT_VALUE_ATTRIBUTE)) {
            columnDefinition.setDefaultValue(columnElement.getAttribute(PoiConstant.DEFAULT_VALUE_ATTRIBUTE));
        }

        //参数信息
        Map<String, Object> properties = addProperties(columnElement);
        columnDefinition.setProperties(properties);

        return columnDefinition;
    }

    private Map<String, Object> addProperties(Element columnElement) {
        Map<String, Object> properties = new HashMap<>();
        if (columnElement.hasAttribute(PoiConstant.WRAPTEXT)) {
            addWrapTextProperties(properties, columnElement.getAttribute(PoiConstant.WRAPTEXT));
        }
        if (columnElement.hasAttribute(PoiConstant.FONT)) {
            addFontProperties(properties, columnElement.getAttribute(PoiConstant.FONT));
        }
        if (columnElement.hasAttribute(PoiConstant.ALIGN_ATTRIBUTE)) {
            addAlignProperties(properties, columnElement.getAttribute(PoiConstant.ALIGN_ATTRIBUTE));
        }
        return properties;
    }

    private boolean hasNotFieldName(Set<String> fieldNames, String determineName) {
        return !fieldNames.contains(determineName);
    }

    public boolean nodeNameEquals(Node node, String desiredName) {
        return desiredName.equals(node.getNodeName());
    }


}
