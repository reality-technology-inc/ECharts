/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.github.abel533.echarts.samples.pie;

import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.TestConfig;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.data.LineData;
import com.github.abel533.echarts.data.PieData;
import com.github.abel533.echarts.series.Pie;
import com.github.abel533.echarts.util.EnhancedOption;
import org.junit.Test;

/**
 * 复杂的时间轴效果
 *
 * @author liuzh
 */
public class PieTest7 implements TestConfig {

    @Test
    public void test() {
        //地址：http://echarts.baidu.com/doc/example/pie7.html

        EnhancedOption option = new EnhancedOption();

        //特殊的自定义时间点
        LineData ld1 = new LineData();
        ld1.name = "2013-06-01";
        ld1.symbol = "emptyStart6";
        ld1.symbolSize = 8;
        LineData ld2 = new LineData();
        ld2.name = "2013-12-01";
        ld2.symbol = "star6";
        ld2.symbolSize = 8;

        //时间轴
        option.timeline().addData("2013-01-01", "2013-02-01", "2013-03-01", "2013-04-01", "2013-05-01",
                ld1, "2013-07-01", "2013-08-01", "2013-09-01", "2013-10-01", "2013-11-01", ld2);
        option.timeline().autoPlay = true;

        option.timeline().label().formatter = "function(s){return s.slice(0,7);}";

        //timeline变态的地方在于多个Option
        Option basic = new Option();
        basic.title().text = "浏览器占比变化";
        basic.title().subtext = "纯属虚构";
        basic.tooltip().trigger = Trigger.item;
        basic.tooltip().formatter = "{a} <br/>{b} : {c} ({d}%)";
        basic.legend().addData("Chrome", "Firefox", "Safari", "IE9+", "IE8-");
        basic.toolbox().show = true;
        basic.toolbox().addFeature(Tool.mark, Tool.dataView, Tool.restore, Tool.saveAsImage);

        int idx = 1;

        Pie bPie = getPie(idx++);
        bPie.center("50%", "45%");
        bPie.radius("50%");
        basic.addSeries(bPie);
        //加入
        option.addOptions(basic);

        //构造11个数据
        Option[] os = new Option[11];
        for (int i = 0; i < os.length; i++) {
            os[i] = getPieOption(idx++);
        }
        option.addOptions(os);

        option.exportToHtml(EXPORT_PATH, "pie7.html");
    }

    /**
     * 获取配置信息
     *
     * @param idx
     * @return
     */
    public Option getPieOption(int idx) {
        Option o = new Option();
        o.addSeries(getPie(idx));
        return o;
    }

    /**
     * 获取饼图数据
     *
     * @param idx
     * @return
     */
    public Pie getPie(int idx) {
        Pie p = new Pie();
        p.name = "浏览器（数据纯属虚构）";
        p.addData(
                new PieData("Chrome", idx * 128 + 80),
                new PieData("Firefox", idx * 64 + 160),
                new PieData("Safari", idx * 32 + 320),
                new PieData("IE9+", idx * 16 + 640),
                new PieData("IE8-", idx * 8 + 1280));
        return p;
    }
}