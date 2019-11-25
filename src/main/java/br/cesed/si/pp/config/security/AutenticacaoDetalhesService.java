package br.cesed.si.pp.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.cesed.si.pp.model.Aluno;
import br.cesed.si.pp.model.Professor;
import br.cesed.si.pp.repository.AlunoRepository;
import br.cesed.si.pp.repository.ProfessorRepository;

@Service
public class AutenticacaoDetalhesService implements UserDetailsService {
	
	@Autowired
	AlunoRepository alunoRepository;
	
	@Autowired
	ProfessorRepository professorRepository;

	@Override
	public UserDetails loadUserByUsername(String email) {
		
		Aluno aluno = alunoRepository.findByEmail(email);
		Professor professor = professorRepository.findByEmail(email);
		
		if(aluno == null && professor == null) {
			throw new UsernameNotFoundException(email);
		}
		
		if(aluno != null) {
			return new AutenticacaoService(aluno.getId(),aluno.getEmail(),aluno.getSenha(), aluno.getTipo()); 
		}
		return new AutenticacaoService(professor.getId(),professor.getEmail(),professor.getSenha(),professor.getTipo());
		
	}
	
	

}
