package org.poi.spring.constants;

import org.poi.spring.annotation.Align;

/**
 * Created by Hong.LvHang on 2017-04-13.
 */
public abstract class PoiConstant {
    private PoiConstant() {

    }

    public static final String CLASS_NAME_SUFFIX = ".EXCLE";


    public static final String TRUE_VALUE = "true";
    public static final String FALSE_VALUE = "false";

    public static final String EXCLE_ID_ATTRIBUTE = "id";
    public static final String DATA_CLASS_ATTRIBUTE = "data-class";
    public static final String EXCLE_NAME_ATTRIBUTE = "excle-name";
    public static final String SHEET_NAME_ATTRIBUTE = "sheet-name";
    public static final String SHEET_INDEX_ATTRIBUTE = "sheet-index";
    public static final String DEFAULT_COLUMN_WIDTH_ATTRIBUTE = "column-width";
    public static final String DEFAULT_ALIGN_ATTRIBUTE = "default-align";
    public static final String DEFAULT_FONT = "default-font";
    public static final String DEFAULT_WRAPTEXT = "default-wraptext";
    //COLUMN
    public static final String COLUMN_ELEMENT = "poi:column";
    public static final String COLUMN_NAME_ATTRIBUTE = "name";
    public static final String TITLE_ATTRIBUTE = "title";
    public static final String REGEX_ATTRIBUTE = "regex";
    public static final String REQUIRED_ATTRIBUTE = "required";
    public static final String ALIGN_ATTRIBUTE = "align";
    public static final String COLUMN_WIDTH_ATTRIBUTE = "column-width";
    public static final String DEFAULT_VALUE_ATTRIBUTE = "default-value";
    public static final String FONT = "font";
    public static final String WRAPTEXT = "wraptext";


    public static final class Column {
        public static final boolean COLUMN_REQUIRED = false;
        public static final String regex = "";
        public static final int with = 20;
        public static final String sheetName = "sheet_1";
        public static final int sheetIndex = 1;
        public static final Align align = Align.GENERAL;
        public static final int font = 12;
        public static final boolean wraptext = true;
        public static final String defauleValue = "";
    }
}
