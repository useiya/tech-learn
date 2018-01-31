package cc.seiya.file.controller;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author libo
 * @date 2018/1/23  22:36
 */
@RestController
public class FileController {

    @PostMapping(value = "upload")
    public String upload(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        File file2Save = new File("d:\\",file.getOriginalFilename());
        FileCopyUtils.copy(bytes,file2Save);
        return file2Save.getAbsolutePath();
    }
}
