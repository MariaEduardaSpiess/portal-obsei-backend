package com.obsei.portal.link_util.link;

import com.obsei.portal.areas_pesquisa.AreaPesquisa;
import com.obsei.portal.link_util.categoria.CategoriaLink;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
@Table(name = "link_util")
public class LinkUtil {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "nome", nullable = false)
	private String nome;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "categoria_link_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private CategoriaLink categoriaLink;
}
