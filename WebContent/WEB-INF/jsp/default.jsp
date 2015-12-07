<t:baseLayout>



<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
<c:forEach items="${scenarii }" var="scenario">

  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingOne">
      <h4 class="panel-title">
        <a role="button" data-toggle="collapse" data-parent="#accordion" href="${scenario.id }" aria-expanded="true" aria-controls="${scenario.id }">
          ${scenario.id }
        </a>
      </h4>
    </div>
    <div id="${scenario.id }" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
      <div class="panel-body">
        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
      </div>
      </div>
      </div>
       </c:forEach>
     
    </div>
 
</t:baseLayout>
