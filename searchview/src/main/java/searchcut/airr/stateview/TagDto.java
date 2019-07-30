package searchcut.airr.stateview;

/**
 * @author zhangming
 * @Date 2019/3/27 11:47
 * @Description: 提供tag字段
 */
public abstract class TagDto {

    protected String mViewTag;

    public TagDto(){
        createTag();
    }

    public abstract void createTag();

    public String getmViewTag() {
        return mViewTag;
    }

    public void setmViewTag(String mViewTag) {
        this.mViewTag = mViewTag;
    }
}
