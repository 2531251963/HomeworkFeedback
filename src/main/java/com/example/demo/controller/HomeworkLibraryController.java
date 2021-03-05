package com.example.demo.controller;

import com.example.demo.common.bean.PageParam;
import com.example.demo.common.bean.PageResultInfo;
import com.example.demo.common.response.Response;
import com.example.demo.common.response.ResponseCode;
import com.example.demo.common.util.JwtUtil;
import com.example.demo.controller.dto.*;
import com.example.demo.controller.vo.HomeworkPublishRecordVo;
import com.example.demo.controller.vo.LibraryHomeworkDetailVo;
import com.example.demo.controller.vo.LibraryHomeworkVo;
import com.example.demo.mgr.bo.HomeworkBo;
import com.example.demo.mgr.bo.LibraryHomeworkDetailBo;
import com.example.demo.service.HomeworkLibraryService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @description 作业相关controller
 * @date 2021/2/20 16:30
 **/
@RestController()
@RequestMapping("/homework_library")
public class HomeworkLibraryController {

    @Resource(name = "homeworkLibraryServiceImpl")
    HomeworkLibraryService homeworkLibraryService;
    @PostMapping("/create_homework")
    public Response<Long> createHomework(@Valid  @RequestBody CreateHomeworkDto createHomeworkDto,@RequestHeader(value = "token") String token) {
        Long userId = JwtUtil.getUserId(token);
        Long homeworkId = homeworkLibraryService.createHomework(createHomeworkDto.getHomeworkName(), createHomeworkDto.getHomeworkNotice(),userId);
        return Response.ok(homeworkId);
    }

    @PostMapping("/save_homework")
    public Response<Boolean> saveHomework(@Valid @RequestBody SaveHomeworkDto saveHomeworkDto) {
        boolean result = homeworkLibraryService.saveHomework(saveHomeworkDto.getHomeworkId(), saveHomeworkDto.getProblemIds());
        return Response.ok(result);
    }

    @GetMapping("/homework_list")
    public Response<PageResultInfo<LibraryHomeworkVo>> homeworkList(@RequestParam("current_page") Integer currentPage,
                                                                    @RequestParam("page_size") Integer pageSize,
                                                                    @RequestHeader(value = "token") String token) {
        Long userId = JwtUtil.getUserId(token);
        PageResultInfo<HomeworkBo> homeworkBoPageResultInfo = homeworkLibraryService.homeworkListPage(userId, new PageParam(currentPage, pageSize));
        List<LibraryHomeworkVo> libraryHomeworkVos = new LibraryHomeworkVo().convertFromHomeworkBoList(homeworkBoPageResultInfo.getList());
        PageResultInfo<LibraryHomeworkVo> pageResultInfo = new PageResultInfo<>(homeworkBoPageResultInfo,libraryHomeworkVos);
        return Response.ok(pageResultInfo);
    }

    @PostMapping("/create_problem")
    public Response<Long> createProblem(@Valid @RequestBody CreateProblemDto createProblemDto,@RequestHeader(value = "token") String token) {
        Long userId = JwtUtil.getUserId(token);
        Pair<ResponseCode, Long> pair = homeworkLibraryService.createProblem(createProblemDto.getProblemType(), createProblemDto.getContent(),userId);
        return Response.buildResponse(pair.getLeft().getCode(),pair.getLeft().getMsg(),pair.getRight());
    }

    @PostMapping("/editor_problem")
    public Response<Boolean> editorProblem(@Valid @RequestBody EditorProblemDto editorProblemDto) {
        Pair<ResponseCode, Boolean> pair = homeworkLibraryService.editorProblem(editorProblemDto.getProblemId(),editorProblemDto.getProblemType(), editorProblemDto.getContent());
        return Response.buildResponse(pair.getLeft().getCode(),pair.getLeft().getMsg(),pair.getRight());
    }

    @GetMapping("/homework_detail")
    public Response<LibraryHomeworkDetailVo> homeworkDetail(@RequestParam("homework_id") Long homeworkId) {
        LibraryHomeworkDetailBo homeworkDetailBo = homeworkLibraryService.homeworkDetail(homeworkId);
        return Response.ok(new LibraryHomeworkDetailVo().convertLibraryHomeworkDetailBo(homeworkDetailBo));
    }

    @PostMapping("/publish_homework")
    public Response<Boolean> publishHomework(@Valid @RequestBody PublishHomeworkDto publishHomeworkDto,@RequestHeader(value = "token") String token) {
        Long userId = JwtUtil.getUserId(token);
        boolean publishHomeworkResult = homeworkLibraryService.publishHomework(publishHomeworkDto.getHomeworkId(), publishHomeworkDto.getClassIds(), publishHomeworkDto.getDeadlineTime(),userId);
        return Response.ok(publishHomeworkResult);
    }

    @GetMapping("/homework_publish_record_list")
    public Response<PageResultInfo<HomeworkPublishRecordVo>> homeworkPublishRecordList(@RequestParam("current_page") Integer currentPage,
                                                                             @RequestParam("page_size") Integer pageSize,
                                                                             @RequestHeader(value = "token") String token) {
        Long userId = JwtUtil.getUserId(token);
        return Response.ok();
    }
}
