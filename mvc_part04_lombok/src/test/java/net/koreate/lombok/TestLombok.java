package net.koreate.lombok;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@EqualsAndHashCode(of = {"tname","tage"})
@RequiredArgsConstructor
public class TestLombok {

	private final int tnum;
	private String tname;
	private int tage;
	private String taddr;
	private String tgender;
}
