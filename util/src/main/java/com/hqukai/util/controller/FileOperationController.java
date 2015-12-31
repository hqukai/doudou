package com.hqukai.util.controller;

import com.hqukai.util.DateUtil;
import com.hqukai.util.FileUtil;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author salim
 * @created 2012-6-4
 * @package com.plaminfo.imusic.action
 */
@Controller
public class FileOperationController {

    /**
     * 文件上传
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/salim/upload", method = RequestMethod.POST)
    public String uploadFile(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {

        String savePath = request.getSession().getServletContext().getRealPath("/") + "upload" + File.separator + "images" + File.separator + DateUtil.getDate("yyyyMM") + File.separator + "avatars" + File.separator; // 图片保存路径
        String saveUrl = "/upload/images/" + DateUtil.getDate("yyyyMM") + "/avatars/";
        // 设置上传文件最大为 3M
        final long MAX_SIZE = 3 * 1024 * 1024;
        // 允许上传的文件格式的列表 final  
        String allowedExt = new String("*.png;*.gif;*.jpg;*.bmp;*.jpeg");

        response.setContentType("text/html"); // 设置字符编码为UTF-8, 这样支持汉字显示  
        response.setCharacterEncoding("UTF-8");

        // 实例化一个硬盘文件工厂,用来配置上传组件  
        DiskFileItemFactory dfif = new DiskFileItemFactory();
        // 设置上传文件时用于临时存放文件的内存大小,这里是4K.多于的部分将临时存在硬盘  
        dfif.setSizeThreshold(4096); // 设置存放临时文件的目录,web根目录下的ImagesUploadTemp目录  
        File tmpFile = new File(savePath);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
        dfif.setRepository(tmpFile);

        // 用以上工厂实例化上传组件  
        ServletFileUpload sfu = new ServletFileUpload(dfif);
        // 设置最大上传尺寸  
        sfu.setSizeMax(MAX_SIZE);

        PrintWriter out = response.getWriter();

        MultipartHttpServletRequest re = (MultipartHttpServletRequest) request;
        Map map = re.getFileMap();

        //key 是input框中的 name 属性 value 得到的MultipartFile对象
        List fileList = new ArrayList();

        for (Object obj : map.values()) {
            MultipartFile file = (MultipartFile) obj;
            fileList.add(file);
        }

        // 没有文件上传  
        if (fileList == null || fileList.size() == 0) {
            out.println("please upload file first <p/>");
            //out.println("<a href=\"upload.html\" target=\"_top\">back</a>"); //   
            return null;
        }

        // 得到所有上传的文件  
        Iterator fileItr = fileList.iterator(); // 循环处理所有文件  
        while (fileItr.hasNext()) {
            MultipartFile fileItem = null;
            String path = null;
            long size = 0;
            // 得到当前文件  
            fileItem = (MultipartFile) fileItr.next();
            // 忽略简单form字段而不是上传域的文件域(<input type="text" />等)  
            if (fileItem == null || fileItem.isEmpty()) {
                continue; // 得到文件的完整路径  
            }
            path = fileItem.getOriginalFilename();
            // 得到文件的大小  
            size = fileItem.getSize();
            if ("".equals(path) || size == 0 || size > MAX_SIZE) {
                out.println("<script type=\"text/javascript\">alert('catch error during file upload as file is too large,please try again!');</script>"); // return; 
                return null;
            }
            // 得到去除路径的文件名  
            String t_name = path.substring(path.lastIndexOf("\\") + 1);
            // 得到文件的扩展名(无扩展名时将得到全名)  
            String t_ext = t_name.substring(t_name.lastIndexOf(".") + 1);
            // 拒绝接受规定文件格式之外的文件类型  
            if (t_ext != null && allowedExt.indexOf(t_ext.toLowerCase()) == -1) {
                out.println("please upload allowed file with correct type <p/>");
                return null;
            }
            try { // 保存文件  
                String datestr = DateUtil.getDate("yyyyMM");
                fileItem.transferTo(new File(savePath + "t_" + datestr + "." + t_ext));
                out.println(saveUrl + "t_" + datestr + "." + t_ext);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }


    @RequestMapping(value = "/salim/file", method = RequestMethod.POST)
    public String uploadFile2(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {

        String savePath = request.getSession().getServletContext().getRealPath("/") + "upload" + File.separator + "file" + File.separator + DateUtil.getDate("yyyyMM") + File.separator + "avatars" + File.separator; // 图片保存路径
        String saveUrl = "/upload/file/" + DateUtil.getDate("yyyyMM") + "/avatars/";
        // 设置上传文件最大为 10M
        final long MAX_SIZE = 10 * 1024 * 1024;
        // 允许上传的文件格式的列表 final  
        String allowedExt = new String("*.xls;*.doc;*.docx;*.rar;*.zip;");

        response.setContentType("text/html"); // 设置字符编码为UTF-8, 这样支持汉字显示  
        response.setCharacterEncoding("UTF-8");

        // 实例化一个硬盘文件工厂,用来配置上传组件  
        DiskFileItemFactory dfif = new DiskFileItemFactory();
        // 设置上传文件时用于临时存放文件的内存大小,这里是4K.多于的部分将临时存在硬盘  
        dfif.setSizeThreshold(4096); // 设置存放临时文件的目录,web根目录下的ImagesUploadTemp目录  
        File tmpFile = new File(savePath);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
        dfif.setRepository(tmpFile);

        // 用以上工厂实例化上传组件  
        ServletFileUpload sfu = new ServletFileUpload(dfif);
        // 设置最大上传尺寸  
        sfu.setSizeMax(MAX_SIZE);

        PrintWriter out = response.getWriter();

        MultipartHttpServletRequest re = (MultipartHttpServletRequest) request;
        Map map = re.getFileMap();

        //key 是input框中的 name 属性 value 得到的MultipartFile对象
        List fileList = new ArrayList();

        for (Object obj : map.values()) {
            MultipartFile file = (MultipartFile) obj;
            fileList.add(file);
        }

        // 没有文件上传  
        if (fileList == null || fileList.size() == 0) {
            out.println("please upload file first <p/>");
            //out.println("<a href=\"upload.html\" target=\"_top\">back</a>"); //   
            return null;
        }

        // 得到所有上传的文件  
        Iterator fileItr = fileList.iterator(); // 循环处理所有文件  
        while (fileItr.hasNext()) {
            MultipartFile fileItem = null;
            String path = null;
            long size = 0;
            // 得到当前文件  
            fileItem = (MultipartFile) fileItr.next();
            // 忽略简单form字段而不是上传域的文件域(<input type="text" />等)  
            if (fileItem == null || fileItem.isEmpty()) {
                continue; // 得到文件的完整路径  
            }
            path = fileItem.getOriginalFilename();
            // 得到文件的大小  
            size = fileItem.getSize();
            if ("".equals(path) || size == 0 || size > MAX_SIZE) {
                out.println("<script type=\"text/javascript\">alert('catch error during file upload as file is too large,please try again!');</script>"); // return; 
                return null;
            }
            // 得到去除路径的文件名  
            String t_name = path.substring(path.lastIndexOf("\\") + 1);
            // 得到文件的扩展名(无扩展名时将得到全名)  
            String t_ext = t_name.substring(t_name.lastIndexOf(".") + 1);
            // 拒绝接受规定文件格式之外的文件类型  
            if (t_ext != null && allowedExt.indexOf(t_ext.toLowerCase()) == -1) {
                out.println("please upload allowed file with correct type <p/>");
                return null;
            }
            try { // 保存文件  
                String datestr = DateUtil.getDate("yyyyMM");
                fileItem.transferTo(new File(savePath + "F_" + datestr + "." + t_ext));
                out.println(saveUrl + "F_" + datestr + "." + t_ext);
                ;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * APK上传
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws IOException
     */

    @RequestMapping(value = "/salim/apk", method = RequestMethod.POST)
    public String uploadFileApk(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {

        String savePath = request.getSession().getServletContext().getRealPath("/") + "download" + File.separator + "apk" + File.separator; // 图片保存路径
        String saveUrl = "/download/apk/";
        // 设置上传文件最大为 10M
        final long MAX_SIZE = 10 * 1024 * 1024;
        // 允许上传的文件格式的列表 final  
        String allowedExt = new String("*.apk;");

        response.setContentType("text/html"); // 设置字符编码为UTF-8, 这样支持汉字显示  
        response.setCharacterEncoding("UTF-8");

        // 实例化一个硬盘文件工厂,用来配置上传组件  
        DiskFileItemFactory dfif = new DiskFileItemFactory();
        // 设置上传文件时用于临时存放文件的内存大小,这里是4K.多于的部分将临时存在硬盘  
        dfif.setSizeThreshold(4096); // 设置存放临时文件的目录,web根目录下的ImagesUploadTemp目录  
        File tmpFile = new File(savePath);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
        dfif.setRepository(tmpFile);

        // 用以上工厂实例化上传组件  
        ServletFileUpload sfu = new ServletFileUpload(dfif);
        // 设置最大上传尺寸  
        sfu.setSizeMax(MAX_SIZE);

        PrintWriter out = response.getWriter();

        MultipartHttpServletRequest re = (MultipartHttpServletRequest) request;
        Map map = re.getFileMap();

        //key 是input框中的 name 属性 value 得到的MultipartFile对象
        List fileList = new ArrayList();

        for (Object obj : map.values()) {
            MultipartFile file = (MultipartFile) obj;
            fileList.add(file);
        }

        // 没有文件上传  
        if (fileList == null || fileList.size() == 0) {
            out.println("please upload file first <p/>");
            //out.println("<a href=\"upload.html\" target=\"_top\">back</a>"); //   
            return null;
        }

        // 得到所有上传的文件  
        Iterator fileItr = fileList.iterator(); // 循环处理所有文件  
        while (fileItr.hasNext()) {
            MultipartFile fileItem = null;
            String path = null;
            long size = 0;
            // 得到当前文件  
            fileItem = (MultipartFile) fileItr.next();
            // 忽略简单form字段而不是上传域的文件域(<input type="text" />等)  
            if (fileItem == null || fileItem.isEmpty()) {
                continue; // 得到文件的完整路径  
            }
            path = fileItem.getOriginalFilename();
            // 得到文件的大小  
            size = fileItem.getSize();
            if ("".equals(path) || size == 0 || size > MAX_SIZE) {
                out.println("<script type=\"text/javascript\">alert('catch error during file upload as file is too large,please try again!');</script>"); // return; 
                return null;
            }
            // 得到去除路径的文件名  
            String t_name = path.substring(path.lastIndexOf("\\") + 1);
            // 得到文件的扩展名(无扩展名时将得到全名)  
            String t_ext = t_name.substring(t_name.lastIndexOf(".") + 1);
            // 拒绝接受规定文件格式之外的文件类型  
            if (t_ext != null && allowedExt.indexOf(t_ext.toLowerCase()) == -1) {
                out.println("please upload allowed file with correct type <p/>");
                return null;
            }
            try {
                //备份APK重命名
                String datestr = DateUtil.getDate("yyyyMM");
                String imgUrl = "/download/apk/yyzl.apk";
                String realPath = request.getSession().getServletContext().getRealPath("/");
                String tt = request.getSession().getServletContext().getRealPath("/") + "download" + File.separator + "apk" + File.separator + "bf" + File.separator;
                File backupFilePath = new File(realPath + "download" + File.separator + "apk" + File.separator + "bf" + File.separator);
                if (backupFilePath.exists() == false) {
                    backupFilePath.mkdir();
                }
                File backupFile = new File(realPath + "download" + File.separator + "apk" + File.separator + "yyzl.apk");
                if (backupFile.exists()) {
                    try {
                        int len;
                        byte[] buf = new byte[1024];
                        FileInputStream fin = new FileInputStream(backupFile);
                        FileOutputStream fou = new FileOutputStream(new File(tt + "yyzl_" + datestr + ".apk"));
                        while ((len = fin.read(buf)) > 0) {
                            fou.write(buf, 0, len);
                        }
                        fin.close();
                        fou.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                // 保存文件
                fileItem.transferTo(new File(savePath + "yyzl" + "." + t_ext));
                out.println(saveUrl + "yyzl" + "." + t_ext);
                ;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * excel文件上传hk
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/salim/upload/excel", method = RequestMethod.POST)
    public
    @ResponseBody
    String uploadExcelFile(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
        System.out.println("文件上传后台");

        String savePath = request.getSession().getServletContext().getRealPath("/") + "upload" + File.separator + "file" + File.separator + "mailbook" + File.separator + DateUtil.getDate("yyyyMM") + File.separator + "avatars" + File.separator; // 图片保存路径
        // 设置上传文件最大为 3M
        final long MAX_SIZE = 10 * 1024 * 1024;
        // 允许上传的文件格式的列表 final
        String allowedExt = new String("*.xls");

        response.setContentType("text/html"); // 设置字符编码为UTF-8, 这样支持汉字显示
        response.setCharacterEncoding("UTF-8");

        // 实例化一个硬盘文件工厂,用来配置上传组件
        DiskFileItemFactory dfif = new DiskFileItemFactory();
        // 设置上传文件时用于临时存放文件的内存大小,这里是4K.多于的部分将临时存在硬盘
        dfif.setSizeThreshold(4096); // 设置存放临时文件的目录,web根目录下的ImagesUploadTemp目录
        File tmpFile = new File(savePath);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
        dfif.setRepository(tmpFile);

        // 用以上工厂实例化上传组件
        ServletFileUpload sfu = new ServletFileUpload(dfif);
        // 设置最大上传尺寸
        sfu.setSizeMax(MAX_SIZE);

        PrintWriter out = response.getWriter();

        MultipartHttpServletRequest re = (MultipartHttpServletRequest) request;
        Map map = re.getFileMap();

        //key 是input框中的 name 属性 value 得到的MultipartFile对象
        List fileList = new ArrayList();

        for (Object obj : map.values()) {
            MultipartFile file = (MultipartFile) obj;
            fileList.add(file);
        }
        System.out.println("测试");

        // 没有文件上传
        if (fileList == null || fileList.size() == 0) {
            out.println("please upload file first <p/>");
            //out.println("<a href=\"upload.html\" target=\"_top\">back</a>"); //
            return null;
        }

        // 得到所有上传的文件
        Iterator fileItr = fileList.iterator(); // 循环处理所有文件
        while (fileItr.hasNext()) {
            MultipartFile fileItem = null;
            String path = null;
            long size = 0;
            // 得到当前文件
            fileItem = (MultipartFile) fileItr.next();
            // 忽略简单form字段而不是上传域的文件域(<input type="text" />等)
            if (fileItem == null || fileItem.isEmpty()) {
                continue; // 得到文件的完整路径
            }
            path = fileItem.getOriginalFilename();
            // 得到文件的大小
            size = fileItem.getSize();
            if ("".equals(path) || size == 0 || size > MAX_SIZE) {
                out.println("<script type=\"text/javascript\">alert('catch error during file upload as file is too large,please try again!');</script>"); // return;
                return null;
            }
            // 得到去除路径的文件名
            String t_name = path.substring(path.lastIndexOf("\\") + 1);
            // 得到文件的扩展名(无扩展名时将得到全名)
            String t_ext = t_name.substring(t_name.lastIndexOf(".") + 1);
            // 拒绝接受规定文件格式之外的文件类型
            if (t_ext != null && allowedExt.indexOf(t_ext.toLowerCase()) == -1) {
                out.println("please upload allowed file with correct type <p/>");
                return null;
            }
            try { // 保存文件
                String datestr = DateUtil.getDate("yyyyMM");
                fileItem.transferTo(new File(savePath + "t_" + datestr + "." + t_ext));
                String tmep = savePath + "t_" + datestr + "." + t_ext;
                out.println(tmep);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 保存专题图片
     *
     * @param request
     * @param response
     * @param model
     */
    @RequestMapping(value = "/delete/file", method = RequestMethod.GET)
    public void deleteFile(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String imgUrl = request.getParameter("typeUrl");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            String path = request.getSession().getServletContext().getRealPath("/") + imgUrl;
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
            out.write("true");
        } catch (Exception e) {
            out.write("flase");
            e.printStackTrace();
        } finally {
            if (out != null)
                out.close();
        }
    }

    @RequestMapping(value = "/file/recovery", method = RequestMethod.GET)
    public void recovery(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String imgUrl = request.getParameter("typeUrl");
        String realPath = request.getSession().getServletContext().getRealPath("/");
        File backupFilePath = new File(realPath + "test");
        if (backupFilePath.exists() == false) {
            backupFilePath.mkdir();
        }
        String imgName = imgUrl.substring(imgUrl.lastIndexOf("/"));
        File backupFile = new File(realPath + "test");
        if (backupFile.exists()) {
            try {
                int len;
                byte[] buf = new byte[1024];
                FileInputStream fin = new FileInputStream(backupFile);
                FileOutputStream fou = new FileOutputStream(new File(realPath + imgUrl));
                while ((len = fin.read(buf)) > 0) {
                    fou.write(buf, 0, len);
                }
                fin.close();
                fou.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            backupFile.delete();
        }
    }

    /**
     * 保存教程图片
     *
     * @param request
     * @param response
     * @param model
     */
    @RequestMapping(value = "/tutorial/file", method = RequestMethod.GET)
    public void deleteTutorial(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String imgUrl = request.getParameter("tutorialUrl");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            String path = request.getSession().getServletContext().getRealPath("/") + imgUrl;
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
            out.write("true");
        } catch (Exception e) {
            out.write("flase");
            e.printStackTrace();
        } finally {
            if (out != null)
                out.close();
        }
    }

    @RequestMapping(value = "/tutorial/recovery", method = RequestMethod.GET)
    public void recoveryTutorial(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String imgUrl = request.getParameter("tutorialUrl");
        String realPath = request.getSession().getServletContext().getRealPath("/");
        File backupFilePath = new File(realPath + "test");
        if (backupFilePath.exists() == false) {
            backupFilePath.mkdir();
        }
        String imgName = imgUrl.substring(imgUrl.lastIndexOf("/"));
        File backupFile = new File(realPath + "test");
        if (backupFile.exists()) {
            try {
                int len;
                byte[] buf = new byte[1024];
                FileInputStream fin = new FileInputStream(backupFile);
                FileOutputStream fou = new FileOutputStream(new File(realPath + imgUrl));
                while ((len = fin.read(buf)) > 0) {
                    fou.write(buf, 0, len);
                }
                fin.close();
                fou.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            backupFile.delete();
        }
    }

    /**
     * 应用助理APK下载
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/down/assistant.apk", method = RequestMethod.GET)
    public String downLoadFile(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String filePath = request.getSession().getServletContext().getRealPath("/").replaceAll("\\-", "") +  //做集群的时候找不到文件
                "upload" + File.separator + "source" + File.separator + "assistant.apk";
        new FileUtil().download(filePath, response);
        return null;
    }
}
