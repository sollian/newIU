package com.sollian.buz.bean;

/**
 * @author sollian on 2017/9/20.
 */

public class Section {
    // 分区数量
    private int       section_count;
    // 分区名称
    private String    name;
    // 分区表述
    private String    description;
    // 是否是根分区
    private boolean   is_root;
    // 该分区所属根分区名称
    private String    parent;
    /**
     * 附加
     */
    // 分区数组
    private Section[] section;
    // 子目录name数组
    private String[]  sub_section;
    // 子分区数组
    private Board[]   board;

    public int getSection_count() {
        return section_count;
    }

    public void setSection_count(int section_count) {
        this.section_count = section_count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean is_root() {
        return is_root;
    }

    public void setIs_root(boolean is_root) {
        this.is_root = is_root;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Section[] getSection() {
        return section;
    }

    public void setSection(Section[] section) {
        this.section = section;
    }

    public String[] getSub_section() {
        return sub_section;
    }

    public void setSub_section(String[] sub_section) {
        this.sub_section = sub_section;
    }

    public Board[] getBoard() {
        return board;
    }

    public void setBoard(Board[] board) {
        this.board = board;
    }
}
