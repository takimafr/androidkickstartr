package com.athomas.androidkickstartr.generator;

import java.io.IOException;

import com.sun.codemodel.JCodeModel;

public interface Generator {

	public JCodeModel generate(JCodeModel jCodeModel) throws IOException;

}
