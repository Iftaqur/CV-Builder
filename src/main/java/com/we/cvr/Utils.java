package com.we.cvr;

import com.we.cvr.models.cvbuilder.*;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static File generatePDF(CV cv, BasicInfo bi, ProfileInfo pi, List<AcademicInfo> ai, List<CareerInfo> ci) {

        //Creating PDF document object
        try {

            System.out.println("Create Simple PDF file with Text");
            String fileName = cv.getCvID() + "-" + cv.getTitle() + ".pdf";//new ClassPathResource("static/uploads/pdf/"+cv.getCvID()+"-"+cv.getTitle()+".pdf").getFile().getPath(); // name of our file


            PDDocument doc = new PDDocument();
            PDPage page = new PDPage();

            doc.addPage(page);

            File bg = new ClassPathResource("static/uploads/images/templates/design" + cv.getTemplate().getId() + ".jpg").getFile();
            File pp = new ClassPathResource("static/uploads/images/templates/pp.jpg").getFile();

            try {
                pp = new ClassPathResource("static/uploads/images/pp/"+cv.getUser().getUserID()+"pp.jpg").getFile();
                System.out.println(pp.getAbsolutePath());
            }catch (Exception e){
                e.printStackTrace();
            }

            Thumbnails.of(pp).size(120, 120).toFile(pp);
            PDImageXObject image = PDImageXObject.createFromFile(bg.getPath(), doc);
            PDImageXObject i = PDImageXObject.createFromFile(pp.getPath(), doc);
            PDPageContentStream content = new PDPageContentStream(doc, page);

            content.drawImage(image, 0, 0);
            content.drawImage(i, 45, 650);

            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 16);
            content.moveTextPositionByAmount(45, 600);
            content.drawString("Contact");
            content.endText();

            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 12);
            content.moveTextPositionByAmount(45, 575);
            content.drawString("Address");
            content.endText();

            String[] addresses = bi.getAddress().split(",");
            int sy = 555;
            for (String s : addresses) {
                content.beginText();
                content.setFont(PDType1Font.HELVETICA_BOLD, 10);
                content.moveTextPositionByAmount(45, sy);
                content.showText(s.trim()); //Address
                content.endText();
                sy -= 15;
            }

            /*content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 10);
            content.moveTextPositionByAmount(45, 555);
            content.drawString("Square, Birmingham B18");
            content.endText();*/

            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 12);
            content.moveTextPositionByAmount(45, 500);
            content.drawString("Phone");
            content.endText();

            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 10);
            content.moveTextPositionByAmount(45, 485);
            content.drawString(bi.getPhone());
            content.endText();

            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 12);
            content.moveTextPositionByAmount(45, 450);
            content.drawString("Email");
            content.endText();

            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 10);
            content.moveTextPositionByAmount(45, 435);
            content.drawString(bi.getEmail());
            content.endText();

            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 12);
            content.moveTextPositionByAmount(45, 410);
            content.drawString("Referance");
            content.endText();

            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 10);
            content.moveTextPositionByAmount(45, 395);
            content.drawString(bi.getReference());
            content.endText();

            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 26);
            content.moveTextPositionByAmount(200, 725);
            content.drawString(bi.getFirstname() + " " + bi.getLastname());
            content.endText();


            /*content.beginText();
            content.setFont(PDType1Font.HELVETICA, 10);
            content.moveTextPositionByAmount(200, 700);
            content.drawString("Customer Service");
            content.endText();*/

            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 16);
            content.moveTextPositionByAmount(200, 660);
            content.drawString("Profile:");
            content.endText();

            String p = pi.getProfile();
            String[] words = p.split(" ");
            p = "";
            int tmpY = 635;

            for (int j = 0; j < words.length; j++) {
                p+=words[j]+" ";
                if((j+1)%15==0){
                    content.beginText();
                    content.setFont(PDType1Font.HELVETICA, 10);
                    content.newLineAtOffset(200, tmpY);
                    content.showText(p);
                    content.endText();
                    p = "";
                    tmpY-=10;
                }
            }

            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 10);
            content.newLineAtOffset(200, tmpY);
            content.showText(p);
            content.endText();
            p = "";
            tmpY-=10;

            tmpY-=25;
           /* p = pi.getSkill();
            words = p.split(" ");
            p = "";
            tmpY-=15;

            for (int j = 0; j < words.length; j++) {
                p+=words[j]+" ";
                if((j+1)%15==0){
                    content.beginText();
                    content.setFont(PDType1Font.HELVETICA, 10);
                    content.newLineAtOffset(200, tmpY);
                    content.showText(p);
                    content.endText();
                    p = "";
                    tmpY-=10;
                }
            }*/
            /*content.beginText();
            content.setFont(PDType1Font.HELVETICA, 10);
            content.newLineAtOffset(200, 615);
            content.drawString("a long history of offering the highest level of service to clients both face to face");
            content.endText();
            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 10);
            content.newLineAtOffset(200, 605);
            content.drawString("online and over the telephone. He has experience of providing a friendly and");
            content.endText();
            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 10);
            content.newLineAtOffset(200, 595);
            content.drawString("efficient service within a fast paced and challenging environment.");
            content.endText();*/


            tmpY-=10;
            //100

            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 16);
            content.moveTextPositionByAmount(200, tmpY);
            content.drawString("Career:");
            content.endText();
            tmpY-=25;


            for (CareerInfo cin : ci){
                content.beginText();
                content.setFont(PDType1Font.HELVETICA, 10);
                content.newLineAtOffset(200, tmpY);
                content.drawString(cin.getDuration());
                content.endText();

                content.beginText();
                content.setFont(PDType1Font.HELVETICA, 10);
                content.newLineAtOffset(275, tmpY);
                content.drawString(cin.getPosition());
                content.endText();

                content.beginText();
                content.setFont(PDType1Font.HELVETICA, 10);
                content.newLineAtOffset(475, tmpY);
                content.drawString(cin.getCompany());
                content.endText();

                tmpY-=15;


                p = cin.getDetails();
                words = p.split(" ");
                p = "";
                tmpY-=20;

                for (int j = 0; j < words.length; j++) {
                    p+=words[j]+" ";
                    if((j+1)%10==0){
                        content.beginText();
                        content.setFont(PDType1Font.HELVETICA, 10);
                        content.newLineAtOffset(200, tmpY);
                        content.showText(p);
                        content.endText();
                        p = "";
                        tmpY-=10;
                    }
                }
                tmpY-=5;

            }

            tmpY-=25;

            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 16);
            content.moveTextPositionByAmount(200, tmpY);
            content.drawString("Skills:");
            content.endText();

            tmpY-=15;

            p = pi.getSkill();
            words = p.split(" ");
            p = "";
            tmpY-=5;

            for (int j = 0; j < words.length; j++) {
                p+=words[j]+" ";
                if((j+1)%15==0){
                    content.beginText();
                    content.setFont(PDType1Font.HELVETICA, 10);
                    content.newLineAtOffset(200, tmpY);
                    content.showText(p);
                    content.endText();
                    p = "";
                    tmpY-=10;
                }
            }
            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 10);
            content.newLineAtOffset(200, tmpY);
            content.showText(p);
            content.endText();
            p = "";
            tmpY-=10;

            tmpY-=25;


            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 16);
            content.moveTextPositionByAmount(200, tmpY);
            content.drawString("Academics:");
            content.endText();

            tmpY-=20;

            for (AcademicInfo ain : ai){
                int offset = 0;

                content.beginText();
                content.setFont(PDType1Font.HELVETICA, 10);
                content.newLineAtOffset(220, tmpY);
                content.drawString("Graduation: "+ain.getEndYear());
                content.endText();


                content.beginText();
                content.setFont(PDType1Font.HELVETICA_BOLD, 10);
                content.newLineAtOffset(355, tmpY);
                content.drawString(ain.getGrade());
                content.endText();

                tmpY-=20;
                content.beginText();
                content.setFont(PDType1Font.HELVETICA, 10);
                content.newLineAtOffset(220, tmpY);
                content.drawString(ain.getDegree());
                content.endText();

                tmpY-=20;
                content.beginText();
                content.setFont(PDType1Font.HELVETICA, 10);
                content.newLineAtOffset(220, tmpY);
                content.drawString(ain.getInstitute());
                content.endText();

                tmpY-=5;

                tmpY-=25;
            }


            content.close();
            doc.save(fileName);
            doc.close();

            System.out.println("your file created in : " + fileName);
            return new File(fileName);

        } catch (IOException e) {

            e.printStackTrace();

        }

        return null;
    }
}
