$(document).ready(function() {
	$("#openNav").click(function() {
		$("#menu").addClass("show-menu");
		$("#menu").removeClass("hide-menu");
	});
	$("#closeNav").click(function() {
		$("#menu").addClass("hide-menu");
		$("#menu").removeClass("show-menu");
	});
	$("#menu").on("swiperight", function() {
		$(this).addClass("show-menu");
		$(this).removeClass("hide-menu");
	});
	$("#menu").on("swipeleft", function() {
		$(this).addClass("hide-menu");
		$(this).removeClass("show-menu");
	});
});

function hideNav() {
	$("#menu").removeClass("hide-menu");
	$("#menu").removeClass("show-menu");
}

window.onresize = hideNav;