<#include "/classic/inc/layout.ftl"/>
<#assign topId = 1 />

<@layout>
<!-- top -->
<@contents channelId=topId pageNo=1 pageSize=3>
    <#if  results.records?size gt 0>

        <div class="row banner">
            <#list results.records as row>
                <div class="banner-item col-xs-12 col-sm-4 col-md-4">
                    <div class="index-banner-box"
                        <#if row.thumbnail?? && row.thumbnail?length gt 0>
                         style="background-image:url(<@resource src=row.thumbnail/>)"
                        <#else>
                         style="background-image:url(${base}/dist/images/spinner-overlay.png)"
                        </#if> >
                        <a class="top" href="${base}/post/${row.id}">
                            <div class="overlay"></div>
                            <div class="line"></div>
                            <div class="title">
                                <h3>${row.title?html}</h3>
                            </div>
                        </a>
                    </div>
                </div>
            </#list>
        </div>
    </#if>
</@contents>

<!-- top/end -->

<div class="row">
    <div class="col-xs-12 col-md-9 side-left">
        <div class="posts">
            <@contents pageNo=pageNo>
            <ul class="posts-list">
                <#include "/classic/inc/posts_item.ftl"/>
                <#list results.records as row>
                    <@posts_item row/>
                </#list>
                <#if  results.records?size == 0>
                    <li class="content">
                        <div class="content-box posts-aside">
                            <div class="posts-item">该目录下还没有内容!</div>
                        </div>
                    </li>
                </#if>
            </ul>
            </@contents>
        </div>
        <div class="text-center">
            <!-- Pager -->
            <@utils.pager request.requestURI!"", results, 5/>
        </div>
    </div>
    <div class="col-xs-12 col-md-3 side-right">
        <#include "/classic/inc/right.ftl"/>
    </div>
</div>

</@layout>