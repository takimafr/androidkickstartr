package com.athomas.androidkickstartr.generator;

import java.io.IOException;

import com.athomas.androidkickstartr.util.RefHelper;
import com.sun.codemodel.JCodeModel;

public interface Generator {

	public JCodeModel generate(JCodeModel jCodeModel, RefHelper ref) throws IOException;

}
