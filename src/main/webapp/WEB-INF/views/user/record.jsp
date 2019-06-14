<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<jsp:include page="../include/header.jsp" />
</head>
<body>
	<jsp:include page="../include/menu.jsp" />
	<div class="page-container">
		<div class="record-filter">
			<form class="form-inline">
				<div class="form-group mb-2">
					<label>Filtros</label>
				</div>
				<div class="form-group mx-sm-3 mb-2">
					<select class="form-control">
						<option>Todos</option>
					</select>
				</div>
				<div class="form-group mx-sm-3 mb-2">
					<select class="form-control">
						<option>2019</option>
					</select>
				</div>
				<button type="submit" class="form-group btn btn-primary mb-2"
					style="background-color: #ffdd00; color: #2d4191;">Buscar</button>
			</form>
		</div>
		<div class="record-results">
			<p>Se han encontrado ${ documents.size() } documento(s).</p>
			<div class="table-responsive">
				<table class="table">
					<tbody>
						<c:forEach var="doc" items="${ documents }">
							<tr>
								<td><i class="fa fa-file-text" aria-hidden="true"></i> ${ doc.name }</td>
								<td><i class="fa fa-eye yo-icons" aria-hidden="true"
									data-toggle="modal" data-target="#exampleModal"
									data-uuid="${ doc.uuid }"></i></td>
								<td><a href="${ contextPath }/Download?node=${ doc.uuid }"><i
										class="fa fa-download yo-icons" aria-hidden="true"></i></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- Modal -->
				<div class="modal fade" id="exampleModal" tabindex="-1"
					role="dialog" aria-labelledby="exampleModalLabel"
					aria-hidden="true">
					<div class="modal-dialog modal-lg" role="document"
						style="max-width: 794px; max-height: 561px;">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">Previsualización</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<c:if test="${ device.equals('smartphone') }">
									<img alt="" src="" width="100%">
								</c:if>
								<c:if test="${ device.equals('pc') }">
									<embed showcontrols="false" style="margin-top: 15px;" src=""
										type="application/pdf" width="100%" height="350">
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../include/footer.jsp" />
	<script type="text/javascript">
		$('#exampleModal').on('show.bs.modal', function(event) {
			var button = $(event.relatedTarget); // Button that triggered the modal
			var uuid = button.data('uuid'); // Extract info from data-* attributes
			// If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
			// Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
			var modal = $(this);
			var path = '${ contextPath }/Preview?node=' + uuid;
			modal.find('.modal-body img').attr('src', path);
			modal.find('.modal-body embed').attr('src', path);
		});
	</script>
</body>
</html>