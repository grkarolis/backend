package com.program.backend.controllers;

import com.program.backend.beans.response.NewsFeedResponse;
import com.program.backend.services.NewsFeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Component
@CrossOrigin(origins = "*")
@RequestMapping(value = "/news")
public class NewsFeedController {

    @Autowired
    private NewsFeedService newsFeedService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<NewsFeedResponse> getAll() {
        return newsFeedService.getNewsFeedResponse(newsFeedService.getAllNewsFedd());
    }

    @RequestMapping(value = "/{pageNumber}", method = RequestMethod.GET)
    public @ResponseBody
    List<NewsFeedResponse> getAll(@PathVariable("pageNumber") int pageNumber) {
        return newsFeedService.getNewsFeedResponse(newsFeedService.getNewsFeedPage(pageNumber));
    }
}
