package com.athomas.androidkickstartr;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.apache.commons.io.FileUtils;

import com.athomas.androidkickstartr.util.StringUtils;

@Path("/")
public class Main {

	@POST
	@Produces("application/zip")
	public Response go(//

			// State
			@FormParam("actionBarSherlock") boolean actionBarSherlock,//
			@FormParam("navigationType") String navigationType,//
			@FormParam("androidAnnotations") boolean androidAnnotations,//
			@FormParam("restTemplate") boolean restTemplate,//
			@FormParam("maven") boolean maven,//
			@FormParam("nineOldAndroids") boolean nineOldAndroids,//
			@FormParam("supportV4") boolean supportV4,//
			@FormParam("acra") boolean acra,//
			@FormParam("eclipse") boolean eclipse,//
			@FormParam("viewPager") boolean viewPager,//
			@FormParam("viewPagerIndicator") boolean viewPagerIndicator,//
			@FormParam("roboguice") boolean roboguice,//
			@FormParam("proguard") boolean proguard,//

			// Application
			@FormParam("packageName") String packageName,//
			@FormParam("name") String name,//
			@FormParam("activity") String activity,//
			@FormParam("activityLayout") String activityLayout,//

            //sdk min target
            @FormParam("sdkMinTarget") int sdkMinTarget,//
            //sdk target
            @FormParam("sdkTarget") int sdkTarget,//
            //sdk max target
            @FormParam("sdkMaxTarget") int sdkMaxTarget//
	) {

		boolean listNavigation = false;
		boolean tabNavigation = false;

		if (navigationType != null) {
			tabNavigation = navigationType.equals("tabNavigation");
			listNavigation = navigationType.equals("listNavigation");
		}

		if (StringUtils.isEmpty(packageName)) {
			packageName = "com.androidkickstarter.app";
		}
		if (StringUtils.isEmpty(name)) {
			name = "MyApplication";
		}
		if (StringUtils.isEmpty(activity)) {
			activity = "MainActivity";
		}
		if (StringUtils.isEmpty(activityLayout)) {
			activityLayout = "activity_main";
		}

        if (sdkMinTarget<8 || sdkMinTarget>17) {
            sdkMinTarget = 8;
        }

        if (sdkTarget<8 || sdkTarget>17) {
            sdkTarget = 17;
        }

        if (sdkMinTarget > sdkTarget){
            sdkMinTarget = 8;
            sdkTarget = 17;
        }

        if(sdkMinTarget > sdkMaxTarget || sdkTarget > sdkMaxTarget) {
            sdkMaxTarget=17;
            sdkMinTarget=8;
            sdkTarget=17;
        }


		if (viewPager && !actionBarSherlock && !viewPagerIndicator && !supportV4) {
			supportV4 = true;
		}

		AppDetails appDetails = new AppDetails.Builder().//

				// Parameters
				packageName(packageName).//
				name(name).//
				activity(activity).//
				activityLayout(activityLayout).//
				minSdk(sdkMinTarget).//
				targetSdk(sdkTarget).//
                maxSdk(sdkMaxTarget).//
				permissions(new ArrayList<String>()).//

				// Libraries
				actionBarSherlock(actionBarSherlock).//
				listNavigation(listNavigation).//
				tabNavigation(tabNavigation).//
				viewPager(viewPager).//
				viewPagerIndicator(viewPagerIndicator).//
				roboguice(roboguice).//
				androidAnnotations(androidAnnotations).//
				restTemplate(restTemplate). //
				maven(maven). //
				nineOldAndroids(nineOldAndroids). //
				supportV4(supportV4). //
				acra(acra). //
				eclipse(eclipse). //
				proguard(proguard). //
				build();

		final Kickstartr kickStarter = new Kickstartr(appDetails);
		final File file = kickStarter.start();

		if (file == null) {
			return Response.serverError().build();
		}

		StreamingOutput output = new StreamingOutput() {
			public void write(OutputStream output) throws IOException, WebApplicationException {
				try {
					FileUtils.copyFile(file, output);
					kickStarter.clean();
				} catch (Exception e) {
					throw new WebApplicationException(e);
				}
			}
		};

		return Response //
				.ok(output) //
				.header("Content-Length", file.length()) //
				.header("Content-Disposition", "attachment; filename=" + file.getName()) //
				.build();
	}

}
