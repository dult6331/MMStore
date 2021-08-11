/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Hfyl
 */
public class StarLib extends SimpleTagSupport {

    private String id;
    private float radius;
    private int stars;
    private int checked;

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        String fillcolor = "#f6ff00";
        float x = radius;
        float width = (radius * 2) * stars;
        float height = radius*2;
        out.print("<canvas id='" + id + "' height=" + height + " width= " + width + "/>");
        for (int i = 1; i <= stars; i++) {
            if (i > checked) {
                fillcolor = "#FFF";
            }
            out.print("<script>\n"
                    + "            var canvas = document.getElementById(\"" + id + "\");\n"
                    + "            var ctx = canvas.getContext(\"2d\");\n"
                    + "            var alpha = (2 * Math.PI) / 10;\n"
                    + "            var radius = " + radius + ";\n"
                    + "            var starXY = [" + x + ", " + radius + "];\n"
                    + "\n"
                    + "            ctx.beginPath();\n"
                    + "\n"
                    + "            for (var i = 11; i != 0; i--)\n"
                    + "            {\n"
                    + "                var r = radius * (i % 2 + 1) / 2;\n"
                    + "                var omega = alpha * i;\n"
                    + "                ctx.lineTo((r * Math.sin(omega)) + starXY[0], (r * Math.cos(omega)) + starXY[1]);\n"
                    + "            }\n"
                    + "            ctx.closePath();\n"
                    + "            ctx.fillStyle = \"" + fillcolor + "\";\n"
                    + "            ctx.fill();\n"
                    + "            ctx.stroke();\n"
                    + "        </script>"
            );
            x += radius * 2;
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

}
