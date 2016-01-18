<t:baseLayout>
	<link href="res/css/launch.css" rel="stylesheet" />
	<div class="container">
		<div class="col-md-12">
			<c:if test="${not empty error}">
				<div class="alert alert-danger alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<strong>Error</strong><br /> ${error}
				</div>
			</c:if>
			<div class="progress" id="progress"></div>
			<audio controls id="audio" src="res/audio/loup.mp3"></audio>
			<div id="messagePanel" class="hidden">
				<div class="col-md-10">
					<div class="alert alert-success alert-dismissible" role="alert">
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<strong><fmt:message key="message_launch_success_1" /></strong><br />
						<a id="visualization_link"><fmt:message key="message_launch_success_2" /></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		window.onload = function onLoad() {
			var time = ${time_progress};
			var circle = new ProgressBar.Circle('#progress', {
				color : '#FCB03C',
				trailWidth : 1,
				duration : time,
				text : {
					className : 'progressbar-text',
					value : "0",
					style : {
						color : "ff",
					}
				},
				step : function(state, bar) {
					bar.setText((bar.value() * 100).toFixed(0));
					if (bar.value() == 1) {
						bar.stop();
						actualize_result();
					}
				},
				finish : function() {
					console.log("hihi");
				}
			});
			circle.animate(1, {
				easing : 'easeInOut'
			}, function() {
			});
			function play_audio() {
				var audio = document.getElementById("audio");

				audio.play();
				setTimeout(function() {
					audio.pause();
				}, 5000);
			}
			function actualize_result() {
				$.ajax({
					url : "getNewResult",
					data : {
						sid : getUrlParameter("id")
					},
					method : "GET"
				}).done(function(msg) {
					result_loaded(msg);
					play_audio();
					$("#visualization_link").attr('href', 'result?rid=' + msg);
					$("#messagePanel").removeClass("hidden");
					$("#progress").addClass("hidden");
				}).fail(function(error) {
					console.log(error);
				});
			}
			function result_loaded(msg) {
				console.log(msg);
			}
		};
	</script>
</t:baseLayout>