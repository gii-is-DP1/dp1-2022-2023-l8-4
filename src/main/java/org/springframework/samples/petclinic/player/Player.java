package org.springframework.samples.petclinic.player;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.statistics.Statistic;
import org.springframework.samples.petclinic.statistics.archivements.Achievement;
import org.springframework.samples.petclinic.user.User;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "players")
@Audited
@EqualsAndHashCode(callSuper = false)
public class Player extends BaseEntity {

	@Column(name = "register_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate registerDate;

	@Column(name = "modification_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate modificationDate;

	@Column(name = "last_login")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate lastLogin;
	
	@Column(name = "email")
	@NotEmpty
	@Email
	private String email;

	@Column(name = "birth_date")
	@NotNull
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate birthDate;

	@Column(name = "profile_picture")
	private String profilePicture;

	@OneToOne(cascade = CascadeType.ALL)
	@NotAudited
    @JoinColumn(name = "username", referencedColumnName = "username")
	@Valid
	private User user;

	@ManyToMany
	@NotAudited
	@JoinTable(name = "players_games", joinColumns = @JoinColumn(name = "player_id"),
	inverseJoinColumns = @JoinColumn(name = "game_id"))
	private Collection<Game> playedGames;

	public void addGame(Game game) {
		getGamesInternal().add(game);
	}

	protected Collection<Game> getGamesInternal() {
		if (this.playedGames == null) {
			this.playedGames = new ArrayList<Game>();
		}
		return this.playedGames;
	}

	 @ManyToMany
	 @NotAudited
	 @JoinTable(name = "players_achievements", joinColumns = @JoinColumn(name = "achievement_id"),
	 inverseJoinColumns = @JoinColumn(name = "player_id"))
	 private Collection<Achievement> playersAchievement;
	 
	 @OneToOne( cascade = CascadeType.ALL )
	 @NotAudited
	 private Statistic statistic;
	
}
