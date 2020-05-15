<#include "/classic/inc/layout.ftl"/>
<@layout "相册列表">
    <div class="row">
        <div class="col-xs-12 col-md-9 side-left">
            <div class="panel panel-default">
                <div class="panel-body streams-tags">
                    <div class="instagram itemscope">
                        <div class="photos">
                            <#list results as row>
                                <section class="archives album" style="">
                                    <h1 class="year">${row.created}</h1>
                                    <ul class="img-box-ul">
                                        <#list row.photoList as photo>
                                            <figure class="thumb">
                                                <a href="${photo.photoUrl}" data-size="640x640" data-type="image" data-target="${photo.photoUrl}" rel="lightbox[${row.created}]" title="${photo.description}">
                                                    <img data-src="${photo.photoUrl}" src="${photo.photoUrl}" itemprop="thumbnail" data-lzled="true"/>
                                                </a>
                                            </figure>
                                        </#list>
                                    </ul>
                                </section>
                            </#list>
                        </div>
                    </div>
                    <#if results?size == 0>
                        <div class="infos">
                            <div class="media-heading">该目录下还没有内容!</div>
                        </div>
                    </#if>
                </div>
            </div>
        </div>

        <div class="col-xs-12 col-md-3 side-right">
            <#include "/classic/inc/right.ftl" />
        </div>

    </div>

    <script type="text/javascript">
        Mediabox.scanPage = function() {
            var links = $$("a").filter(function(el) {
                return el.rel && el.rel.test(/^lightbox/i);
            });
            $$(links).mediabox({/* Put custom options here */}, null, function(el) {
                var rel0 = this.rel.replace(/[[]|]/gi," ");
                var relsize = rel0.split(" ");
                return (this == el) || ((this.rel.length > 8) && el.rel.match(relsize[1]));
            });
        };
        window.addEvent("domready", Mediabox.scanPage);
    </script>
</@layout>

