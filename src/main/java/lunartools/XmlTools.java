package lunartools;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlTools {

    public static String getChildNodeValue(Node node) {
        if(node.hasChildNodes()) {
            return node.getFirstChild().getNodeValue();
        }
        return null;
    }

    public static String getNodeAttribute(Node node, String name) {
    	return ((Element)node).getAttribute(name);
    }

    public static Node getChildNodeByName(Node node, String name) {
        NodeList nodeList=node.getChildNodes();
        for(int i=0;i<nodeList.getLength();i++) {
            Node nodeChild=nodeList.item(i);
            if(name.equals(nodeChild.getNodeName())) {
                return nodeChild;
            }
        }
        return null;
    }

}
