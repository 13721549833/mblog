<#include "/classic/inc/layout.ftl"/>

<@layout "标签:" + kw>

<div class="row streams">
    <div class="col-xs-12 col-md-9 side-left">
        <div class="posts ">
            <ul class="posts-list">
                <li class="content">
                    <div class="content-box posts-aside">
                        <div class="posts-item">标签: ${name} 共 ${results.totalElements} 个结果.</div>
                    </div>
                </li>
                <#include "/classic/inc/posts_item.ftl"/>
                <#list results.records as row>
                    <@posts_item row.post/>
                </#list>
                <#if !results?? || results.records?size == 0>
                    <li class="content">
                        <div class="content-box posts-aside">
                            <div class="posts-item">该目录下还没有内容!</div>
                        </div>
                    </li>
                </#if>
            </ul>
        </div>
        <div class="text-center">
            <@utils.pager request.requestURI, results, 5/>
        </div>
    </div>
    <div class="col-xs-12 col-md-3 side-right">
		<#include "/classic/inc/right.ftl" />
    </div>
</div>
</@layout>