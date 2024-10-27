package com.maxprods.applocker.ui.model;

public class MaxApps_Block {
    private String play_fof_maxappss_id;
    private boolean play_fof_maxappss_block;

    public MaxApps_Block(String id, boolean block) {
        this.play_fof_maxappss_id = id;
        this.play_fof_maxappss_block = block;
    }

    public String getId() {
        return play_fof_maxappss_id;
    }

    public void setId(String id) {
        this.play_fof_maxappss_id = id;
    }

    public boolean isBlock() {
        return play_fof_maxappss_block;
    }

    public void setBlock(boolean block) {
        this.play_fof_maxappss_block = block;
    }
}
