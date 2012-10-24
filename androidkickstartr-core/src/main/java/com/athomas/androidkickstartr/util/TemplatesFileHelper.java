package com.athomas.androidkickstartr.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.athomas.androidkickstartr.model.Application;
import com.athomas.androidkickstartr.model.State;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TemplatesFileHelper {

	private final static Logger LOGGER = LoggerFactory.getLogger(TemplatesFileHelper.class);

	private final FileHelper fileHelper;
	private final Application application;
	private final State state;

	public TemplatesFileHelper(Application application, State state, FileHelper fileHelper) {
		this.application = application;
		this.state = state;
		this.fileHelper = fileHelper;
	}

	public void generate() throws IOException, TemplateException {
		Configuration cfg = new Configuration();

		cfg.setDirectoryForTemplateLoading(fileHelper.getTemplatesDir());
		cfg.setObjectWrapper(new DefaultObjectWrapper());

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("Application", application);
		params.put("ApplicationClassName", application.getApplicationClassName());
		params.put("State", state);

		processTemplate(cfg, params, "Strings.ftl", fileHelper.getTargetStringsFile());
		LOGGER.debug("strings.xml created");

		if (state.isAndroidAnnotations()) {
			application.setActivity(application.getActivity() + "_");
		}

		processTemplate(cfg, params, "AndroidManifest.ftl", fileHelper.getTargetAndroidManifestFile());
		LOGGER.debug("AndroidManifest.xml created");

		processTemplate(cfg, params, "Styles.ftl", fileHelper.getTargetStylesFile());
		LOGGER.debug("styles.xml created");
		
		processTemplate(cfg, params, "ActivityMain.ftl", fileHelper.getTargetActivityMainFile());
		LOGGER.debug("activity_main.xml created");
		

		if (state.isMaven()) {
			processTemplate(cfg, params, "Pom.ftl", fileHelper.getTargetPomFile());
			LOGGER.debug("pom.xml created");
		}

		if (state.isEclipse()) {
			processTemplate(cfg, params, "Project.ftl", fileHelper.getTargetProjectFile());
			LOGGER.debug(".project created");

			processTemplate(cfg, params, "ProjectProperties.ftl", fileHelper.getTargetProjectPropertiesFile());
			LOGGER.debug("project.properties created");

			if (!state.isMaven()) {
				processTemplate(cfg, params, "Classpath.ftl", fileHelper.getTargetClasspathFile());
				LOGGER.debug(".classpath created");
			}
		}

		if (state.isAndroidAnnotations()) {
			processTemplate(cfg, params, "FactoryPath.ftl", fileHelper.getTargetFactoryPathFile());
			LOGGER.debug("factorypath.xml created");
		}
	}

	private void processTemplate(Configuration cfg, HashMap<String, Object> params, String templateFile, File targetFile) throws IOException, TemplateException {
		FileWriter pomWriter = new FileWriter(targetFile);
		Template pomTemplate = cfg.getTemplate(templateFile);
		pomTemplate.process(params, pomWriter);
	}

}
