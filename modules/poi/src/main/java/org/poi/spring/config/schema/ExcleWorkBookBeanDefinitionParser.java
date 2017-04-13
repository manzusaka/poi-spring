package org.poi.spring.config.schema;

import org.apache.poi.ss.usermodel.CellStyle;
import org.poi.spring.config.ColumnDefinition;
import org.poi.spring.config.ExcelWorkBookBeandefinition;
import org.poi.spring.exception.ExcelException;
import org.poi.spring.util.ReflectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.AbstractBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.ClassUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by oldflame on 2017/4/8.
 */
public class ExcleWorkBookBeanDefinitionParser extends AbstractBeanDefinitionParser implements ExcleWorkBookNode {
    private static final String CLASS_NAME_PREFIX = "excleworkbook_";

    private static final Logger logger = LoggerFactory.getLogger(ExcleWorkBookBeanDefinitionParser.class);

    private final Class<?> beanClass;

    public ExcleWorkBookBeanDefinitionParser(Class<ExcelWorkBookBeandefinition> beanDefinitionClass) {
        this.beanClass = beanDefinitionClass;
    }

    protected AbstractBeanDefinition parseInternal(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(beanClass);
        beanDefinition.setLazyInit(false);

        //excleName
        if (!element.hasAttribute(EXCLE_NAME_ATTRIBUTE)) {
            throw new ExcelException("excleworkbook excle-name has empty");
        }
        String excleName = element.getAttribute(EXCLE_NAME_ATTRIBUTE);
        beanDefinition.getPropertyValues().addPropertyValue("excleName", excleName);

        String dataClassName = element.getAttribute(DATA_CLASS_ATTRIBUTE);
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

        String id = CLASS_NAME_PREFIX + dataClassName;
        beanDefinition.getPropertyValues().addPropertyValue("id", id);

        if (element.hasAttribute(SHEET_NAME_ATTRIBUTE)) {
            beanDefinition.getPropertyValues().addPropertyValue("sheetName", element.getAttribute(SHEET_NAME_ATTRIBUTE));
        }
        if (element.hasAttribute(DEFAULT_COLUMN_WIDTH_ATTRIBUTE)) {
            beanDefinition.getPropertyValues().addPropertyValue("defaultColumnWidth", element.getAttribute(DEFAULT_COLUMN_WIDTH_ATTRIBUTE));
        }
        if (element.hasAttribute(DEFAULT_ALIGN_ATTRIBUTE)) {
            String defaultAlign = element.getAttribute(DEFAULT_ALIGN_ATTRIBUTE);
            //获取cell对齐方式的常量值
            short constValue = ReflectUtil.getConstValue(CellStyle.class, "ALIGN_" + defaultAlign.toUpperCase());
            beanDefinition.getPropertyValues().addPropertyValue("defaultAlign", constValue);
        }
        if (element.hasAttribute(SHEET_INDEX_ATTRIBUTE)) {
            beanDefinition.getPropertyValues().addPropertyValue("sheetIndex", element.getAttribute(SHEET_INDEX_ATTRIBUTE));
        }
        List<ColumnDefinition> columnDefinitions = parseColumnElements(element, dataClass, excleName);
        beanDefinition.getPropertyValues().addPropertyValue("columnDefinitions", columnDefinitions);
        parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
        return beanDefinition;
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
            if (nodeNameEquals(node, COLUMN_ELEMENT)) {
                columnDefinitions.add(parseColumnElement((Element) node, fieldNames, excleName));
            }
        }
        return columnDefinitions;
    }


    private ColumnDefinition parseColumnElement(Element columnElement, Set<String> fieldNames, String excleName) {
        ColumnDefinition columnDefinition = new ColumnDefinition();
        //excleName
        if (!columnElement.hasAttribute(COLUMN_NAME_ATTRIBUTE)) {
            throw new ExcelException("excleworkbook column-name has empty excleName=" + excleName);
        }
        String name = columnElement.getAttribute(COLUMN_NAME_ATTRIBUTE);
        if (hasNotFieldName(fieldNames, name)) {
            throw new ExcelException("excleworkbook column-name has empty excleName=" + excleName + "  name=" + name);
        }
        columnDefinition.setName(name);

        if (!columnElement.hasAttribute(TITLE_ATTRIBUTE)) {
            throw new ExcelException("excleworkbook title has empty excleName=" + excleName + " column-name=" + name);
        }
        columnDefinition.setTitle(columnElement.getAttribute(TITLE_ATTRIBUTE));

        if (columnElement.hasAttribute(REGEX_ATTRIBUTE)) {
            columnDefinition.setRegex(columnElement.getAttribute(REGEX_ATTRIBUTE));
        }
        if (columnElement.hasAttribute(REQUIRED_ATTRIBUTE)) {
            columnDefinition.setRequired(TRUE_VALUE.equals(columnElement.getAttribute(REQUIRED_ATTRIBUTE)));
        }
        if (columnElement.hasAttribute(ALIGN_ATTRIBUTE)) {
            String align = columnElement.getAttribute(ALIGN_ATTRIBUTE);
            short constValue = ReflectUtil.getConstValue(CellStyle.class, "ALIGN_" + align.toUpperCase());
            columnDefinition.setAlign(constValue);
        }
        if (columnElement.hasAttribute(COLUMN_WIDTH_ATTRIBUTE)) {
            try {
                columnDefinition.setColumnWidth(Integer.parseInt(columnElement.getAttribute(COLUMN_WIDTH_ATTRIBUTE)));
            } catch (Exception e) {
                throw new ExcelException("excleworkbook column-width has error excleName=" + excleName + " column-name=" + name);
            }
        }
        if (columnElement.hasAttribute(DEFAULT_VALUE_ATTRIBUTE)) {
            columnDefinition.setDefaultValue(columnElement.getAttribute(DEFAULT_VALUE_ATTRIBUTE));
        }
        return columnDefinition;
    }

    private boolean hasNotFieldName(Set<String> fieldNames, String determineName) {
        return !fieldNames.contains(determineName);
    }

    public boolean nodeNameEquals(Node node, String desiredName) {
        return desiredName.equals(node.getNodeName());
    }
}
