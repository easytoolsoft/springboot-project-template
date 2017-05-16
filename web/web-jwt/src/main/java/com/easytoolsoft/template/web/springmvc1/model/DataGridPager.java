package com.easytoolsoft.template.web.springmvc1.model;

import com.easytoolsoft.mybatis.pager.PageInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * gridview控件分页类 <br>
 *
 * @author zhiwei.deng
 * @date 2017-03-25
 */
@Data
@NoArgsConstructor
public class DataGridPager {
    private Integer page = 1;
    private Integer rows = 50;
    private String sort = "id";
    private String order = "desc";

    public PageInfo toPageInfo() {
        return this.toPageInfo("");
    }

    public PageInfo toPageInfo(final String tablePrefix) {
        final String prefix = StringUtils.defaultString(tablePrefix, "").trim();
        final String name = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(this.sort), '_');
        final String sortField = prefix + StringUtils.defaultString(name, "").toLowerCase();
        return new PageInfo((this.page - 1) * this.rows,
            this.rows, sortField, this.order);
    }
}
