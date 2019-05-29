package com.github.abel533.echarts.samples.pie

import com.github.abel533.echarts.code.*
import com.github.abel533.echarts.data.Data
import com.github.abel533.echarts.series.Pie
import com.github.abel533.echarts.style.ItemStyle
import com.github.abel533.echarts.style.TextStyle
import com.github.abel533.echarts.style.itemstyle.Emphasis
import com.github.abel533.echarts.util.EnhancedOption
import org.junit.Test

class PieTest8 {

    @Test
    fun test() {

        val option = EnhancedOption()

        option.tooltip().trigger(Trigger.item).formatter("{a} <br/>{b}: {c} ({d}%)")
        option.legend().orient(Orient.vertical).x(X.left).data("Chrome","Firefox","IE","Safari","Opera")


        val p1 = Pie("1")
        p1.radius("50%", "70%").avoidLabelOverlap(false).label().emphasis(Emphasis().show(true).textStyle(TextStyle().fontSize(30).fontWeight(FontWeight.bold))).normal().show(false).position(Position.center)
                p1.data(Data("Chrome", 68),Data("Firefox", 10),Data("IE", 10), Data("Safari", 2) ,Data("Opera", 10))

        option.series(p1)
        option.exportToHtml("pie8.html")
        option.view()

    }

}