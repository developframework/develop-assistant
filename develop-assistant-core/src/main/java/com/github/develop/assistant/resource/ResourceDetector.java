package com.github.develop.assistant.resource;

import java.io.File;

/**
 * 资源探测器
 */
public class ResourceDetector {

    private static final String folder = System.getProperty("user.dir") + File.separator + "resource";

    private File resourceDir;

    public ResourceDetector() {
        resourceDir = new File(folder);
        resourceDir.mkdir();
    }

    public ResourceRepository discovery() {
        ResourceRepository resourceRepository = new ResourceRepository();
        File[] files = resourceDir.listFiles(File::isFile);
        for (File file : files) {
            resourceRepository.put(file.getName(), new FileResource(file));
        }
        return resourceRepository;
    }
}
