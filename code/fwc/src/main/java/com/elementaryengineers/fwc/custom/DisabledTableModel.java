package com.elementaryengineers.fwc.custom;

import javax.swing.table.DefaultTableModel;

/**
 * Created by sarahakk on 4/28/16.
 */
public class DisabledTableModel extends DefaultTableModel {

    @Override
    public boolean isCellEditable(int row, int column){
        return false;
    }
}