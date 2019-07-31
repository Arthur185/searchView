package searchcut.airr.searchview;

import searchcut.airr.stateview.TagDto;

/**
 * @author wangfei
 * @date 2019/7/29.
 */
public class SearchItem extends TagDto {

    private SearchModelDto searchDataDto;

    @Override
    public void createTag() {

    }

    public SearchItem(String mViewTag, SearchModelDto searchDataDto) {
        this.searchDataDto = searchDataDto;
        this.mViewTag = mViewTag;
    }

    public SearchModelDto getSearchDataDto() {
        return searchDataDto;
    }

    public void setSearchDataDto(SearchDataDto searchDataDto) {
        this.searchDataDto = searchDataDto;
    }
}
