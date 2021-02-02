package com.we.cvr.controllers.cvbuilder;

import com.we.cvr.Constants;
import com.we.cvr.Utils;
import com.we.cvr.models.auth.User;
import com.we.cvr.models.cvbuilder.*;
import com.we.cvr.services.cvbuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;

@Controller
public class CvController {
    @Autowired
    private CvManagerService cvManagerService;

    @Autowired
    private BasicInfoService basicInfoService;

    @Autowired
    private ProfileInfoService profileInfoService;

    @Autowired
    private AcademicInfoService academicInfoService;

    @Autowired
    private CareerInfoService careerInfoService;

    @Autowired
    private TemplateService templateService;

    /*@RequestMapping("/cv")
    public List<CV> allCV() {
        return cvManagerService.getAllCV();
    }*/

    @GetMapping("/cv/{id}")
    public CV detailCV(@PathVariable int id) {
        return cvManagerService.getCV(id);
    }

    @GetMapping("/cv/basic-info")
    public String createCvForm(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Constants.USER_TAG);
        if (user == null)
            return "redirect:/login";

        model.addAttribute("basicInfo", basicInfoService.getBasicInfo(user));
        isProfileComplete(user, model);
        return Constants.CV_CREATE_BASIC_INFO_FORM;
    }

    @PostMapping("/cv/basic-info")
    public String createCvFormPost(@RequestParam("photo") MultipartFile photo, @ModelAttribute BasicInfo basicInfo, Model model, HttpServletRequest request) {
        //System.out.println("basicInfo.getFirstname()");
        //System.out.println(basicInfo.getFirstname());
        User user = (User) request.getSession().getAttribute(Constants.USER_TAG);
        if (user == null)
            return "redirect:/login";

        try {
            Files.write(Paths.get("src\\main\\resources\\static\\uploads\\images\\pp\\"+user.getUserID()+"pp.jpg"), photo.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(basicInfo.getFirstname());
        if (basicInfoService.getBasicInfo(user) != null)
            basicInfo.setId(basicInfoService.getBasicInfo(user).getId());
        basicInfoService.addBasicInfo(basicInfo, user);
        model.addAttribute("basicInfo", basicInfoService.getBasicInfo(user));
        return "redirect:/cv/basic-info";
    }

    @GetMapping("/cv/profile-info")
    public String createCvProfileForm(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Constants.USER_TAG);
        if (user == null)
            return "redirect:/login";
        model.addAttribute("profileInfo", profileInfoService.getProfileInfo(user));
        isProfileComplete(user, model);
        return Constants.CV_CREATE_PROFILE_INFO_FORM;
    }

    @PostMapping("/cv/profile-info")
    public String createCvProfileForm(@ModelAttribute ProfileInfo profileInfo, Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Constants.USER_TAG);
        if (user == null)
            return "redirect:/login";

        if (profileInfoService.getProfileInfo(user) != null)
            profileInfo.setId(profileInfoService.getProfileInfo(user).getId());
        profileInfoService.addProfileInfo(profileInfo, user);
        model.addAttribute("profileInfo", profileInfoService.getProfileInfo(user));

        return "redirect:/cv/profile-info";
    }

    @GetMapping("/cv/academic-info")
    public String createCvAcademicForm(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Constants.USER_TAG);
        if (user == null)
            return "redirect:/login";

        model.addAttribute("academicInfoList", academicInfoService.getAcademicInfo(user));
        isProfileComplete(user, model);
        return Constants.CV_CREATE_ACADEMIC_INFO_FORM;
    }

    @PostMapping("/cv/academic-info")
    public String createCvAcademicFormPost(@ModelAttribute AcademicInfo academicInfo, Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Constants.USER_TAG);
        if (user == null)
            return "redirect:/login";

        academicInfoService.addAcademicInfo(academicInfo, user);

        model.addAttribute("academicInfoList", academicInfoService.getAcademicInfo(user));

        return "redirect:/cv/academic-info";
    }

    @PostMapping("/cv/academic-info/{id}")
    public String createCvAcademicFormDelete(@ModelAttribute AcademicInfo academicInfo, @PathVariable int id, Model model, HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute(Constants.USER_TAG);
        if (user == null)
            return "redirect:/login";

        academicInfoService.deleteAcademicInfo(id, user);

        model.addAttribute("academicInfoList", academicInfoService.getAcademicInfo(user));

        return "redirect:/cv/academic-info";
    }

    @GetMapping("/cv/career-info")
    public String createCvCareerForm(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Constants.USER_TAG);
        if (user == null)
            return "redirect:/login";

        model.addAttribute("careerInfoList", careerInfoService.getCareerInfo(user));
        isProfileComplete(user, model);
        return Constants.CV_CREATE_CAREER_INFO_FORM;
    }

    @PostMapping("/cv/career-info")
    public String createCvCareerFormPost(@ModelAttribute CareerInfo careerInfo, Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Constants.USER_TAG);
        if (user == null)
            return "redirect:/login";

        careerInfoService.addCareerInfo(careerInfo, user);

        model.addAttribute("careerInfoList", careerInfoService.getCareerInfo(user));

        return "redirect:/cv/career-info";
    }

    @PostMapping("/cv/career-info/{id}")
    public String createCvCareerFormDelete(@ModelAttribute CareerInfo academicInfo, @PathVariable int id, Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Constants.USER_TAG);
        if (user == null)
            return "redirect:/login";

        careerInfoService.deleteCareerInfo(id, user);

        model.addAttribute("careerInfoList", careerInfoService.getCareerInfo(user));

        return "redirect:/cv/career-info";
    }

    @GetMapping("/cv/project-info")
    public String createCvProjectForm(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Constants.USER_TAG);
        if (user == null)
            return "redirect:/login";
        return Constants.CV_CREATE_PROJECT_INFO_FORM;
    }

    @PostMapping(value = "/cv")
    public void addCV(@RequestBody CV cv, @PathVariable int userID) {
        cvManagerService.addCV(cv, userID);
    }

    /*@PutMapping(value = "/cv/edit/{cvID}")
    public void updateCV(@RequestBody CV cv, @PathVariable int cvID){
        cvManagerService.updateCV(cv, cvID, userID);
    }

    @DeleteMapping(value = "/cv/delete/{cvID}")
    public void deleteCV(@PathVariable int userID, @PathVariable int cvID){
        cvManagerService.deleteCV(cvID, userID);
    }*/
    @GetMapping({"/", "/welcome"})
    public String welcome(Model model, HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute(Constants.USER_TAG);

        if (user == null)
            return "redirect:/login";

        model.addAttribute(Constants.USER_TAG, user);
        model.addAttribute("cvList", cvManagerService.getUsersCV(user.getUserID()));
        model.addAttribute("templates", templateService.getAllTemplates());
        isProfileComplete(user, model);
        return Constants.DASHBOARD_PAGE;
    }

    @GetMapping("/cv/choose-template")
    public String chooseTemplate(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Constants.USER_TAG);
        if (user == null)
            return "redirect:/login";
        model.addAttribute("templates", templateService.getAllTemplates());
        return Constants.CHOOSE_TEMPLATE;
    }


    @PostMapping("/cv/choose-template")
    public String generateCV(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Constants.USER_TAG);
        if (user == null)
            return "redirect:/login";

        Integer id = Integer.parseInt(request.getParameter("templateId"));

        CV cv = new CV();

        cv.setUser(user);
        cv.setTemplate(templateService.getTemplate(id));

        cv.setTitle(user.getName() + " " + Calendar.getInstance().getTimeInMillis());
        cvManagerService.addCV(cv, user.getUserID());
        //cvManagerService.addCV();
        return "redirect:/";
    }

    @GetMapping("/cv/delete/{id}")
    String deleteCV( @PathVariable int id, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Constants.USER_TAG);
        if (user == null)
            return "redirect:/login";

        cvManagerService.deleteCV(id, user.getUserID());
        return "redirect:/";
    }

    @GetMapping("/cv/download/{id}")
    ResponseEntity<InputStreamResource> downloadCV(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(Constants.USER_TAG);
        if (user == null) {
            try {
                response.sendRedirect("/login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        CV cv = cvManagerService.getCV(id);
        File file = Utils.generatePDF(cv, basicInfoService.getBasicInfo(user), profileInfoService.getProfileInfo(user),
                academicInfoService.getAcademicInfo(user), careerInfoService.getCareerInfo(user)
                );

        System.out.println(file==null);
        try {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            //cvManagerService.deleteCV(id, user.getUserID());
            return ResponseEntity.ok()
                    // Content-Disposition
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                    // Content-Type
                    .contentType(MediaType.APPLICATION_PDF)
                    // Contet-Length
                    .contentLength(file.length()) //
                    .body(resource);
            //return "redirect:/";
        }catch (IOException e){
            return null;
        }
    }

    void isProfileComplete(User user, Model model) {
        if (basicInfoService.getBasicInfo(user) == null || profileInfoService.getProfileInfo(user) == null ||
                academicInfoService.getAcademicInfo(user) == null || academicInfoService.getAcademicInfo(user).isEmpty() ||
                careerInfoService.getCareerInfo(user) == null || careerInfoService.getCareerInfo(user).isEmpty()
        ) {
            model.addAttribute("isProfileComplete", false);
            return;
        }
        model.addAttribute("isProfileComplete", true);
    }
}
