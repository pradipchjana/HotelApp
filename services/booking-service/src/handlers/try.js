import { PDFDocument, rgb, StandardFonts } from "npm:pdf-lib";

export const generatePDFFile = async (bookingId, amount = 10000) => {
  const pdfDoc = await PDFDocument.create();

  const page = pdfDoc.addPage([594, 841]);

  const font = await pdfDoc.embedFont(StandardFonts.Helvetica);

  page.drawText("Hotel Booking Invoice", {
    x: 50,
    y: 350,
    size: 24,
    font,
    color: rgb(0, 0, 0),
  });

  page.drawText(`Booking ID: ${bookingId}`, {
    x: 50,
    y: 300,
    size: 16,
    font,
  });

  page.drawText(`Amount: ${amount}`, {
    x: 50,
    y: 270,
    size: 16,
    font,
  });

  const pdfBytes = await pdfDoc.save();
  const pdfPath = `./receipts/invoice-${bookingId}.pdf`;

  await Deno.writeFile(pdfPath, pdfBytes);

  return pdfPath;
};
