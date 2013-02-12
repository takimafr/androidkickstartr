package com.athomas.androidkickstartr.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.athomas.androidkickstartr.AppDetails;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TemplatesFileHelper {

	private final static Logger LOGGER = LoggerFactory.getLogger(TemplatesFileHelper.class);

	private final FileHelper fileHelper;
	private final AppDetails appDetails;

	public TemplatesFileHelper(AppDetails appDetails, FileHelper fileHelper) {
		this.appDetails = appDetails;
		this.fileHelper = fileHelper;
	}

	public void generate() throws IOException, TemplateException {
		Configuration cfg = new Configuration();

		cfg.setDirectoryForTemplateLoading(fileHelper.getTemplatesDir());
		cfg.setObjectWrapper(new DefaultObjectWrapper());

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("application", appDetails);
		params.put("applicationClassName", appDetails.getApplicationClassName());

		processTemplate(cfg, params, "Strings.ftl", fileHelper.getTargetStringsFile());
		LOGGER.debug("strings.xml created");

		if (appDetails.isAndroidAnnotations()) {
			appDetails.setActivity(appDetails.getActivity() + "_");
		}

		processTemplate(cfg, params, "AndroidManifest.ftl", fileHelper.getTargetAndroidManifestFile());
		LOGGER.debug("AndroidManifest.xml created");

		processTemplate(cfg, params, "Styles.ftl", fileHelper.getTargetStylesFile());
		LOGGER.debug("styles.xml created");

		processTemplate(cfg, params, "ActivityMain.ftl", fileHelper.getTargetActivityMainFile());
		LOGGER.debug("activity_main.xml created");

		if (appDetails.isMaven()) {
			processTemplate(cfg, params, "Pom.ftl", fileHelper.getTargetPomFile());
			LOGGER.debug("pom.xml created");
		}

		if (appDetails.isEclipse()) {
			processTemplate(cfg, params, "Project.ftl", fileHelper.getTargetProjectFile());
			LOGGER.debug(".project created");

			processTemplate(cfg, params, "ProjectProperties.ftl", fileHelper.getTargetProjectPropertiesFile());
			LOGGER.debug("project.properties created");

			if (!appDetails.isMaven()) {
				processTemplate(cfg, params, "Classpath.ftl", fileHelper.getTargetClasspathFile());
				LOGGER.debug(".classpath created");
			}
		}

		if (appDetails.isAndroidAnnotations()) {
			processTemplate(cfg, params, "FactoryPath.ftl", fileHelper.getTargetFactoryPathFile());
			LOGGER.debug("factorypath.xml created");
		}

		if (appDetails.isRoboguice() && appDetails.isActionBarSherlock()) {
			processTemplate(cfg, params, "robosherlock/RoboSherlockActivity.ftl", fileHelper.getTargetRoboSherlockActivityFile());
			LOGGER.debug("RoboSherlockActivity.java created");
			if (appDetails.isViewPager()) {
				processTemplate(cfg, params, "robosherlock/RoboSherlockFragment.ftl", fileHelper.getTargetRoboSherlockFragmentFile());
				LOGGER.debug("RoboSherlockFragment.java created");

				processTemplate(cfg, params, "robosherlock/RoboSherlockFragmentActivity.ftl", fileHelper.getTargetRoboSherlockFragmentActivityFile());
				LOGGER.debug("RoboSherlockFragmentActivity.java created");
			}
		}
	}

	private void processTemplate(Configuration cfg, HashMap<String, Object> params, String templateFile, File targetFile) throws IOException, TemplateException {
		FileWriter pomWriter = new FileWriter(targetFile);
		Template pomTemplate = cfg.getTemplate(templateFile);
		pomTemplate.process(params, pomWriter);
	}

}
