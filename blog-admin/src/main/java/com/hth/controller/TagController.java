package com.hth.controller;

import com.hth.domain.Dto.TagListDto;
import com.hth.domain.ResponseResult;
import com.hth.domain.entity.Tag;
import com.hth.domain.vo.PageVo;
import com.hth.domain.Dto.AddTagDto;
import com.hth.domain.vo.TagVo;
import com.hth.service.TagService;
import com.hth.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    /**
     * 查询标签列表
     * @param pageNum
     * @param pageSize
     * @param tagListDto
     * @return
     */
    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto){
        return tagService.list(pageNum, pageSize, tagListDto);
    }

    /**
     * 新增标签
     * @param addTagDto
     * @return
     */
    @PostMapping
    public ResponseResult addTag(@RequestBody AddTagDto addTagDto){
        Tag tag = BeanCopyUtils.copyBean(addTagDto,Tag.class);
        tagService.save(tag);
        return ResponseResult.okResult();
    }

    /**
     * 删除标签
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseResult deleteTag(@PathVariable Long id){
        tagService.removeById(id);
        return ResponseResult.okResult();
    }

    /**
     * 获取标签信息
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseResult getInfo(@PathVariable(value = "id") Long id){
        Tag tag = tagService.getById(id);
        return ResponseResult.okResult(tag);
    }

    @PutMapping
    public ResponseResult updateTag(@RequestBody TagListDto tagListDto){
        Tag tag = BeanCopyUtils.copyBean(tagListDto, Tag.class);
        tagService.updateById(tag);
        return ResponseResult.okResult();
    }

    /**
     * 查询所有标签接口
     * @return
     */
    @GetMapping("/listAllTag")
    public ResponseResult listAllTag(){
        List<TagVo> tagVos = tagService.listAllTag();
        return ResponseResult.okResult(tagVos);
    }
}
