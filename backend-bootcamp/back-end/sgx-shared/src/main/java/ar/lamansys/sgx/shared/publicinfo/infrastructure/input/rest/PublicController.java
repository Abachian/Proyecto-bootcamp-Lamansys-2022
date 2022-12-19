package ar.lamansys.sgx.shared.publicinfo.infrastructure.input.rest;

import ar.lamansys.sgx.shared.featureflags.application.AppFeature;
import ar.lamansys.sgx.shared.flavor.FlavorService;
import ar.lamansys.sgx.shared.publicinfo.application.ApplicationVersionService;
import ar.lamansys.sgx.shared.publicinfo.infrastructure.input.dto.ApplicationVersionDto;
import ar.lamansys.sgx.shared.publicinfo.infrastructure.input.dto.PublicInfoDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/public")
@Tag(name = "Public", description = "Public")
public class PublicController {

	private final String flavor;

	private final ApplicationVersionService applicationVersionService;

	public PublicController(
			FlavorService flavorService,
			ApplicationVersionService applicationVersionService) {
		super();
		this.flavor = flavorService.getFlavor().toString();
		this.applicationVersionService = applicationVersionService;
	}

	@GetMapping(value = "/info")
	public @ResponseBody
	PublicInfoDto getInfo() {
		return new PublicInfoDto(
			flavor,
			Arrays.stream(AppFeature.values()).filter(AppFeature::isActive).collect(Collectors.toSet())
		);
	}

	@GetMapping(value = "/version")
	public ResponseEntity<ApplicationVersionDto> getApplicationVersion() {
		ApplicationVersionDto result = new ApplicationVersionDto(applicationVersionService.getCurrentVersion());
		return ResponseEntity.ok(result);
	}

}