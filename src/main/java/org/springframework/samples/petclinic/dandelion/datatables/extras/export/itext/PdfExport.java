/*
 * [The "BSD licence"]
 * Copyright (c) 2013-2015 Dandelion
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * 3. Neither the name of Dandelion nor the names of its contributors 
 * may be used to endorse or promote products derived from this software 
 * without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.springframework.samples.petclinic.dandelion.datatables.extras.export.itext;

import java.io.OutputStream;

import com.github.dandelion.core.DandelionException;
import com.github.dandelion.datatables.core.export.DatatablesExport;
import com.github.dandelion.datatables.core.export.ExportConf;
import com.github.dandelion.datatables.core.export.ExportConf.Orientation;
import com.github.dandelion.datatables.core.export.ReservedFormat;
import com.github.dandelion.datatables.core.html.HtmlColumn;
import com.github.dandelion.datatables.core.html.HtmlRow;
import com.github.dandelion.datatables.core.html.HtmlTable;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * <p>
 * PDF implementation of {@link DatatablesExport} that exports data in PDF
 * format using iText.
 * </p>
 * 
 * @author Thibault Duchateau
 * @author Xiao Xi Liu
 */
public class PdfExport implements DatatablesExport {

	private HtmlTable table;
	private ExportConf exportConf;

	private Font chsFont = FontFactory.getFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
	//private Font chtFont = FontFactory.getFont("Mkai-Medium", "UniCNS-UCS2-H", BaseFont.NOT_EMBEDDED);
	private Font jpFont = FontFactory.getFont("HeiseiKakuGo-W5", "UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED);
	private Font korFont = FontFactory.getFont("HYSMyeongJoStd-Medium", "UniKS-UCS2-H", BaseFont.NOT_EMBEDDED);

	@Override
	public void initExport(HtmlTable table) {
		this.table = table;
		this.exportConf = table.getTableConfiguration().getExportConfigurations().get(ReservedFormat.PDF);
	}

	@Override
	public void processExport(OutputStream output) {

		Document document = null;
		if (exportConf.getOrientation() != null && exportConf.getOrientation().equals(Orientation.LANDSCAPE)) {
			document = new Document(PageSize.LETTER.rotate());
		} else {
			document = new Document();
		}

		PdfWriter pdfWriter;
		try {
			pdfWriter = PdfWriter.getInstance(document, output);
			pdfWriter.setViewerPreferences(PdfWriter.PageLayoutSinglePage);

			document.open();
			addTitle(document);
			addTable(document);

		} catch (DocumentException e) {
			StringBuilder sb = new StringBuilder("Something went wrong during the PDF generation of the table '");
			sb.append(table.getOriginalId());
			sb.append("' and with the following export configuration: ");
			sb.append(exportConf.toString());
			throw new DandelionException(sb.toString(), e);
		} finally {
			document.close();
		}
	}

	private void addTitle(Document document) throws DocumentException {
		Paragraph title = new Paragraph(exportConf.getFileName());
		title.add(new Paragraph(" ")); // empty line
		title.setAlignment(Element.ALIGN_CENTER);
		document.add(title);
	}

	private void addTable(Document document) throws DocumentException {

		PdfPCell cell = null;

		// Compute the column count in order to initialize the iText table
		int columnCount = table.getLastHeaderRow().getColumns(ReservedFormat.ALL, ReservedFormat.PDF).size();

		if (columnCount != 0) {

			PdfPTable pdfTable = new PdfPTable(columnCount);
			pdfTable.setWidthPercentage(100f);

			// Header
			if (exportConf != null && exportConf.getIncludeHeader()) {

				for (HtmlRow htmlRow : table.getHeadRows()) {

					for (HtmlColumn column : htmlRow.getColumns(ReservedFormat.ALL, ReservedFormat.PDF)) {

						cell = new PdfPCell();
						cell.setPhrase(buildPhrase(column.getContent().toString()));
						pdfTable.addCell(cell);
					}
				}
			}

			for (HtmlRow htmlRow : table.getBodyRows()) {

				for (HtmlColumn column : htmlRow.getColumns(ReservedFormat.ALL, ReservedFormat.PDF)) {

					cell = new PdfPCell();
					cell.setPhrase(buildPhrase(column.getContent().toString()));
					pdfTable.addCell(cell);
				}
			}

			document.add(pdfTable);
		}
	}

	private Phrase buildPhrase(String content) {
		// choose font according to the characters in context
		Phrase phrase;
		if (CharUtil.containsKorean(content)) {
			phrase = new Phrase(content, korFont);
		} else if (CharUtil.containsJapanese(content)) {
			phrase = new Phrase(content, jpFont);
		} else if (CharUtil.containsChinese(content)) {
			phrase = new Phrase(content, chsFont);
		} else {
			phrase = new Phrase(content);
		}
		return phrase;
	}
}