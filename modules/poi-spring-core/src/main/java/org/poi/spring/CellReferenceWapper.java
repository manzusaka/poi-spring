package org.poi.spring;

import org.apache.poi.hssf.util.CellReference;

/**
 * Created by Hong.LvHang on 2017-05-09.
 */
public class CellReferenceWapper {
    //cell ref
    private CellReference cellReference;
    //bean field name
    private String fieldName;
    //cell value
    private String text;

    public CellReferenceWapper(CellReference cellReference, String text,String fieldName) {
        this.cellReference = cellReference;
        this.text = text;
        this.fieldName = fieldName;
    }

    public CellReference getCellReference() {
        return cellReference;
    }

    public void setCellReference(CellReference cellReference) {
        this.cellReference = cellReference;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
