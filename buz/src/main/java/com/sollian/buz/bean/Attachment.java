package com.sollian.buz.bean;

/**
 * @author sollian on 2017/9/4.
 */

public class Attachment {
    // 文件列表
    private File[] file;
    // 剩余空间大小
    private String remain_space;
    // 剩余附件个数
    private int    remain_count;

    public File[] getFile() {
        return file;
    }

    public void setFile(File[] file) {
        this.file = file;
    }

    public String getRemain_space() {
        return remain_space;
    }

    public void setRemain_space(String remain_space) {
        this.remain_space = remain_space;
    }

    public int getRemain_count() {
        return remain_count;
    }

    public void setRemain_count(int remain_count) {
        this.remain_count = remain_count;
    }

    /**
     * 附件中包含的文件类
     *
     * @author 守宪
     */
    public class File {

        // 文件名
        private String name;
        // 文件链接，在用户空间的文件，该值为空
        private String url;
        // 文件大小
        private String size;
        // 宽度为120px的缩略图，用户空间的文件，该值为空,附件为图片格式(jpg,png,gif)存在
        private String thumbnail_small;
        // 宽度为240px的缩略图，用户空间的文件，该值为空,附件为图片格式(jpg,png,gif)存在
        private String thumbnail_middle;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getThumbnail_small() {
            return thumbnail_small;
        }

        public void setThumbnail_small(String thumbnail_small) {
            this.thumbnail_small = thumbnail_small;
        }

        public String getThumbnail_middle() {
            return thumbnail_middle;
        }

        public void setThumbnail_middle(String thumbnail_middle) {
            this.thumbnail_middle = thumbnail_middle;
        }

        public String getWrappedUrl() {
            //TODO:
            return getUrl();
        }

        public String getWrappedThumbnail_small() {
            //TODO:
            return getThumbnail_small();
        }

        public String getWrappedThumbnail_middle() {
            //TODO:
            return getThumbnail_middle();
        }
    }
}