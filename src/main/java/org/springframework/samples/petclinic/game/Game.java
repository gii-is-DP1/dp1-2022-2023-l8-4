package org.springframework.samples.petclinic.game;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.card.Card;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.player.Player;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "games")
@ToString
@EqualsAndHashCode(callSuper=false)
public class Game extends BaseEntity {

	@Column(name = "date")
	@NotEmpty
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name = "game_mode")
	@NotEmpty
	private GameMode gameMode;
	
	@ManyToMany
	@NotEmpty
	@JoinTable(name = "players_games", joinColumns = @JoinColumn(name = "game_id"),
	inverseJoinColumns = @JoinColumn(name = "player_id"))
	private Collection<Player> players;
	
	@ManyToMany(cascade = {CascadeType.PERSIST,
					CascadeType.MERGE,
					CascadeType.DETACH,
					CascadeType.REFRESH})
	private Collection<Card> cards;
}
