package com.duan.blogos.service.impl.audience;

import com.duan.blogos.service.config.preference.DbProperties;
import com.duan.blogos.service.dao.blog.BlogCategoryDao;
import com.duan.blogos.service.dao.blog.BlogLabelDao;
import com.duan.blogos.service.dto.blog.BlogListItemDTO;
import com.duan.blogos.service.entity.blog.Blog;
import com.duan.blogos.service.entity.blog.BlogCategory;
import com.duan.blogos.service.entity.blog.BlogLabel;
import com.duan.blogos.service.entity.blog.BlogStatistics;
import com.duan.blogos.service.impl.BlogFilterAbstract;
import com.duan.blogos.service.manager.DataFillingManager;
import com.duan.blogos.service.restful.ResultModel;
import com.duan.blogos.service.service.audience.BlogRetrievalService;
import com.duan.common.util.CollectionUtils;
import com.duan.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created on 2017/12/19.
 * 读者检索博文
 *
 * @author DuanJiaNing
 */
@Service
public class BlogRetrievalServiceImpl extends BlogFilterAbstract<ResultModel<List<BlogListItemDTO>>> implements
        BlogRetrievalService {

    @Autowired
    private BlogCategoryDao categoryDao;

    @Autowired
    private BlogLabelDao labelDao;

    @Autowired
    private DataFillingManager dataFillingManager;

    @Autowired
    private DbProperties dbProperties;

    @Override
    protected ResultModel<List<BlogListItemDTO>> constructResult(Map<Long, Blog> blogHashMap,
                                                                 List<BlogStatistics> statistics,
                                                                 Map<Long, Long[]> blogIdMapCategoryIds,
                                                                 Map<Long, String> blogImgs) {

        // 重组结果
        List<BlogListItemDTO> result = new ArrayList<>();
        String ch = dbProperties.getStringFiledSplitCharacterForNumber();

        for (BlogStatistics ss : statistics) {
            Long blogId = ss.getBlogId();
            Blog blog = blogHashMap.get(blogId);

            // category
            Long[] cids = blogIdMapCategoryIds.get(blogId);
            List<BlogCategory> categories = null;
            if (!CollectionUtils.isEmpty(cids)) {
                categories = categoryDao.listCategoryById(cids);
            }

            // label
            Long[] lids = StringUtils.longStringDistinctToArray(blog.getLabelIds(), ch);
            List<BlogLabel> labels = null;
            if (!CollectionUtils.isEmpty(lids)) {
                labels = labelDao.listLabelById(lids);
            }

            String blogImg = blogImgs.get(blogId);
            BlogListItemDTO dto = dataFillingManager.blogListItemToDTO(ss,
                    CollectionUtils.isEmpty(categories) ? null : categories.toArray(new BlogCategory[0]),
                    CollectionUtils.isEmpty(labels) ? null : labels.toArray(new BlogLabel[0]),
                    blog, blogImg);

            result.add(dto);
        }

        return new ResultModel<>(result);

    }
}
