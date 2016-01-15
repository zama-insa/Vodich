<t:baseLayout>
<div class="col-md-10">
			<c:if test="${not empty error}">
				<div class="alert alert-danger alert-dismissible" role="alert">
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				  <strong>Error</strong><br/> ${error}
				</div>
			</c:if>
			
			</div>
			
<c:if test ="${empty error}">
<p>Launch Done<p>
</c:if>
 <div class="progress" id="progress"></div>

        <!-- These are probably out dated so you might want to use newest versions -->
        
<style>
            .progress {
                height: 400px;
            }

            .progress > svg {
                height: 100%;
                margin-left:auto;
                margin-righ:auto;
                width: 100%;
            }
            .progressbar-text{
            	font-weight: bold !important;
            	font-size: xx-large !important;
            }
            #audio{
            	display: none;
            }
</style>
<audio controls id="audio" src="res/audio/loup.mp3"></audio>
<script>
window.onload = function onLoad() {
	var time = ${time_progress};
    var circle = new ProgressBar.Circle('#progress', {
        color: '#FCB03C',
        trailWidth: 1,
        duration: time,
        text:{
        	className: 'progressbar-text',
        	value:"0",
        	style:{
        		color:"ff",
        	}
        },
        step: function(state, bar) {
            bar.setText((bar.value() * 100).toFixed(0));
          	if(bar.value()==1){
          		bar.stop();
          		play_audio();
          	}
        }
    });
    circle.animate(1, function() {
        circle.animate(1);
    })
	function play_audio(){
    	var audio = document.getElementById("audio"); 

   		audio.play();
   		setTimeout(function(){audio.pause();},5000);
    }
};

</script>  
</t:baseLayout>