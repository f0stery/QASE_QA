package tests.api;


import models.api.create_defect.CreateDefectRq;
import models.api.create_defect.CreateDefectRs;
import models.api.create_project.CreateProjectRq;
import models.api.create_project.CreateProjectRs;
import org.testng.Assert;
import org.testng.annotations.Test;

import static adapters.ProjectAPI.*;
import static tests.api.ProjectAPITest.PROJECT_CODE;
import static tests.api.ProjectAPITest.PROJECT_NAME;

public class DefectAPITest {

    @Test
    public void checkCreateDefect() {
        CreateProjectRq projectRq = CreateProjectRq.builder()
                .title(PROJECT_NAME)
                .code(PROJECT_CODE)
                .description("defects")
                .access("all")
                .group("test")
                .build();

        CreateProjectRs projectRs = createProject(projectRq);
        Assert.assertEquals(projectRs.status, true);
        Assert.assertEquals(projectRs.result.code, PROJECT_CODE);

        CreateDefectRq defectRq = CreateDefectRq.builder()
                .title("test")
                .actual_result("test")
                .severity(4)
                .milestoneId(1)
                .build();
        CreateDefectRs defectRs = createDefect(defectRq, PROJECT_CODE);
        Assert.assertEquals(defectRs.status, true);
        Assert.assertEquals(defectRs.result.id, 1);

        deleteProject(projectRs.result.code);
    }
}
