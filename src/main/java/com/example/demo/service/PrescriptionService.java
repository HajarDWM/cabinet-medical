package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.DTO.PrescriptionDTO;
import com.example.demo.Entity.Consultation;
import com.example.demo.Repository.ConsultationRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Prescription;
import com.example.demo.Repository.PrescriptionRepository;

@Service
public class PrescriptionService {

	@Autowired
	private PrescriptionRepository prescriptionRepository;

	@Autowired
	private ConsultationRepository consultationRepository;

	// Mapper داخلي -> DTO
	private PrescriptionDTO toDTO(Prescription p) {
		Long consultationId = (p.getConsultation() != null) ? p.getConsultation().getIdConsul() : null;
		return new PrescriptionDTO(
				p.getIdPerscription(),
				p.getMedication(),
				p.getDosage(),
				p.getDuration(),
				p.getNotes(),
				consultationId
		);
	}

	// GET الكل بصيغة DTO (Projection من الريبو)
	public List<PrescriptionDTO> getAllDTO() {
		return prescriptionRepository.findAllAsDTO();
	}

	// GET بالـ Entity (إلى احتجتيه)
	public Optional<PrescriptionDTO> getById(Long id) {
		return prescriptionRepository.findById(id).map(this::toDTO);
	}

	// CREATE from DTO
	public PrescriptionDTO create(@Valid PrescriptionDTO dto) {
		Prescription p = new Prescription();
		p.setMedication(dto.getMedication());
		p.setDosage(dto.getDosage());
		p.setDuration(dto.getDuration());
		p.setNotes(dto.getNotes());

		// ربط الـ Prescription مع Consultation (FK بلا select)
		Consultation c = consultationRepository.getReferenceById(dto.getConsultationId());
		p.setConsultation(c);

		p = prescriptionRepository.save(p);
		return toDTO(p);
	}

	// UPDATE from DTO
	public PrescriptionDTO update(Long id, @Valid PrescriptionDTO dto) {
		Prescription p = prescriptionRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Prescription not found"));

		if (dto.getMedication() != null && !dto.getMedication().isBlank()) p.setMedication(dto.getMedication());
		if (dto.getDosage() != null && !dto.getDosage().isBlank()) p.setDosage(dto.getDosage());
		if (dto.getDuration() != null && !dto.getDuration().isBlank()) p.setDuration(dto.getDuration());
		if (dto.getNotes() != null && !dto.getNotes().isBlank()) p.setNotes(dto.getNotes());

		if (dto.getConsultationId() != null) {
			Consultation c = consultationRepository.getReferenceById(dto.getConsultationId());
			p.setConsultation(c);
		}

		p = prescriptionRepository.save(p);
		return toDTO(p);
	}

	// DELETE
	public void delete(Long id) {
		prescriptionRepository.deleteById(id);
	}

	// (اختياري) جميع الوصفات حسب Consultation
	public Optional<PrescriptionDTO> getByConsultationId(Long idConsul) {
		return prescriptionRepository.findByConsultation_IdConsul(idConsul)
				.map(this::toDTO);
	}
}
