import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DomParser {

    public static void main(String[] args) {

        Journal journal = new Journal();
        Document doc;

        try{
            doc=buildDocument();
        }catch (Exception e){
            System.out.println("Open parsing error" + e.toString());
            return;
        }

        Node journalNode = doc.getFirstChild();

        NodeList journalChilds = journalNode.getChildNodes();

        String mainName = null;
        Node contactsNode = null;

        for (int i = 0; i < journalChilds.getLength(); i++) {
            if (journalChilds.item(i).getNodeType() != Node.ELEMENT_NODE){
                continue;
            }
            System.out.println("AAA " + journalChilds.item(i).getNodeName());

            switch (journalChilds.item(i).getNodeName()){
                case "title":{
                    mainName = journalChilds.item(i).getTextContent();
                    ///System.out.println("mainName " + mainName);
                }
            }
            NodeList journalChilds2 = journalChilds.item(i).getChildNodes();
            for (int j=0; j<journalChilds2.getLength(); j++){
                if (journalChilds2.item(j).getNodeType() != Node.ELEMENT_NODE){
                    continue;
                }
                System.out.println("BBB " + journalChilds2.item(j).getNodeName());

                NodeList journalChilds3 = journalChilds2.item(j).getChildNodes();
                for (int m=0; m<journalChilds3.getLength(); m++) {
                    if (journalChilds3.item(m).getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }

                    System.out.println("CCC " + journalChilds3.item(m).getNodeName());

                    NodeList journalChilds4 = journalChilds3.item(m).getOwnerDocument().getElementsByTagName("hotkeys");
                    for (int k=0; k<journalChilds4.getLength(); k++) {
                        if (journalChilds3.item(k).getNodeType() != Node.ELEMENT_NODE) {
                            continue;
                        }
                       // System.out.println("DDD " + journalChilds3.item(k).getNodeName());

                    }
                }
            }
        }
        journal.setTitle(mainName);
        if (contactsNode ==null){
            return;
        }

        List<Journal> journalList = new ArrayList<>();
        NodeList contactsChilds = contactsNode.getChildNodes();
        for (int i = 0; i < contactsChilds.getLength(); i++) {
            if (contactsChilds.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if(!contactsChilds.item(i).getNodeName().equals("article")){
                continue;
            }

            String title = "";
            String author = "";
           NodeList elementArticles = contactsChilds.item(i).getChildNodes();

            for (int j = 0; j < elementArticles.getLength(); j++) {
                if (contactsChilds.item(i).getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                switch (contactsChilds.item(i).getNodeName()){
                    case "title":{
                        title = contactsChilds.item(i).getTextContent();
                    }
                    case  "author":{
                        author = contactsChilds.item(i).getTextContent();
                    }

                }

            }

        }    }

    private static Document buildDocument() throws Exception {
        File file = new File("src/main/resources/journal.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        return dbf.newDocumentBuilder().parse(file);
    }


}
