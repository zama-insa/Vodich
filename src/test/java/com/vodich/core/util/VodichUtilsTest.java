package com.vodich.core.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import org.junit.Test;

public class VodichUtilsTest {

	@Test
	public void isNullOrEmptyTest() {
		assertTrue(VodichUtils.isNullOrEmpty(null));
		assertTrue(VodichUtils.isNullOrEmpty(""));
		assertTrue(VodichUtils.isNullOrEmpty("         "));
		assertTrue(VodichUtils.isNullOrEmpty("\n"));
		assertFalse(VodichUtils.isNullOrEmpty("s"));
		assertFalse(VodichUtils.isNullOrEmpty("  s "));
		assertFalse(VodichUtils.isNullOrEmpty("\\n"));
	}

	@Test
	public void readExistingResourceTest() throws FileNotFoundException {
		String filename = "readExistingResource.testfile";
		String text = "42 36\n";
		assertEquals(text, VodichUtils.readResource(filename));
	}

	@Test(expected = FileNotFoundException.class)
	public void readNonExistingResourceTest() throws FileNotFoundException {
		VodichUtils.readResource("nimportequoi");
	}
}
