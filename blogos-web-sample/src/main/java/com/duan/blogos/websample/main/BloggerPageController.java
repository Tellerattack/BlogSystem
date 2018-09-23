package com.duan.blogos.websample.main;

import com.duan.blogos.service.dto.blogger.*;
import com.duan.blogos.service.enums.BloggerPictureCategoryEnum;
import com.duan.blogos.service.exception.CodeMessage;
import com.duan.blogos.service.restful.ResultModel;
import com.duan.blogos.service.service.blogger.*;
import com.duan.blogos.service.service.common.OnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Created on 2018/2/5.
 * 博主主页
 *
 * @author DuanJiaNing
 */
@Controller
@RequestMapping("/{bloggerName}")
public class BloggerPageController {

    @Autowired
    private BloggerAccountService accountService;

    @Autowired
    private BloggerProfileService bloggerProfileService;

    @Autowired
    private BloggerStatisticsService statisticsService;

    @Autowired
    private BloggerPictureService bloggerPictureService;

    @Autowired
    private OnlineService onlineService;

    @Autowired
    private BloggerSettingService settingService;

    @RequestMapping("/archives")
    public ModelAndView mainPage(HttpServletRequest request,
                                 @PathVariable String bloggerName) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("blogger/main");

        BloggerAccountDTO account = accountService.getAccount(bloggerName);
        if (account == null) {
            request.setAttribute("code", CodeMessage.BLOGGER_UNKNOWN_BLOGGER.getCode());
            mv.setViewName("/blogger/register");
            return mv;
        }

        mv.addObject("pageOwnerBloggerId", account.getId());
        mv.addObject("pageOwnerBloggerName", account.getUsername());

        Long ownerId = account.getId();
        BloggerProfileDTO profile = bloggerProfileService.getBloggerProfile(ownerId);
        mv.addObject("blogName", profile.getIntro());
        mv.addObject("aboutMe", profile.getAboutMe());

        BloggerPictureDTO defaultPicture = bloggerPictureService.getDefaultPicture(BloggerPictureCategoryEnum.DEFAULT_BLOGGER_AVATAR);
        mv.addObject("avatarId",
                Optional.ofNullable(profile.getAvatarId())
                        .orElse(defaultPicture == null ? null : defaultPicture.getId()));

        ResultModel<BloggerStatisticsDTO> ownerBgStat = statisticsService.getBloggerStatistics(ownerId);
        mv.addObject("ownerBgStat", ownerBgStat.getData());

        Long loginBgId;
        String token = ""; // TODO redis + token 维护会话
        if ((loginBgId = onlineService.getLoginBloggerId(token)) != -1) {
            ResultModel<BloggerStatisticsDTO> loginBgStat = statisticsService.getBloggerStatistics(loginBgId);
            mv.addObject("loginBgStat", loginBgStat.getData());
        }

        BloggerSettingDTO setting = settingService.getSetting(ownerId);
        mv.addObject("setting", setting);

        return mv;
    }

}
