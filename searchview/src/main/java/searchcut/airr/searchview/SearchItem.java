package searchcut.airr.searchview;

import searchcut.airr.stateview.TagDto;

/**
 * @author wangfei
 * @date 2019/7/29.
 */
public class SearchItem extends TagDto {

    private SearchDataDto searchDataDto;

    @Override
    public void createTag() {

    }

    public SearchItem(String mViewTag, SearchDataDto searchDataDto) {
        this.searchDataDto = searchDataDto;
        this.mViewTag = mViewTag;
    }

    public SearchDataDto getSearchDataDto() {
        return searchDataDto;
    }

    public void setSearchDataDto(SearchDataDto searchDataDto) {
        this.searchDataDto = searchDataDto;
    }
}
