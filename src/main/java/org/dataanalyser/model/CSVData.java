package org.dataanalyser.model;

import java.util.Map;
import java.util.Set;

import org.dataanalyser.exception.CSVDataException;

public class CSVData {

	private long id;
	private long decision;
	private Map<String, Long> vars;
	private boolean removed = false;

	public CSVData(long id, long decision, Map<String, Long> vars) {
		this.id = id;
		setDecision(decision);
		setVars(vars);
	}

	private void setDecision(long decision) {
		if (decision == 0 || decision == 1)
			this.decision = decision;
		else
			throw new CSVDataException("This value (" + decision + ") is a invalid value for decision. The value must be 0 or 1.");
	}

	private void setVars(Map<String, Long> vars) {
		if (vars != null && vars.size() > 0)
			this.vars = vars;
		else
			throw new CSVDataException("Variables can't be NULL or don't values!");
	}

	public Set<String> getVariables() {
		return vars.keySet();
	}

	public long getId() {
		return id;
	}

	public long getDecision() {
		return decision;
	}

	public Map<String, Long> getVars() {
		return vars;
	}

	public Long getVar(String var) {
		return vars.get(var);
	}

	public boolean isRemoved() {
		return removed;
	}

	public void evaluate(Map<String, Long> fMin, Map<String, Long> fMax) {
		if (decision == 1)
			removed = false;
		else {
			removed = true;

			for (String var : vars.keySet()) {
				if (fMin.get(var) != null && fMax.get(var) != null) {
					long min = fMin.get(var);
					long max = fMax.get(var);
					long value = vars.get(var);

					removed = removed && (value < min || value > max);
				}
				else
					removed = false;
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (decision ^ (decision >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((vars == null) ? 0 : vars.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CSVData other = (CSVData) obj;
		if (decision != other.decision)
			return false;
		if (id != other.id)
			return false;
		if (vars == null) {
			if (other.vars != null)
				return false;
		}
		else
			if (!vars.equals(other.vars))
				return false;
		return true;
	}

}
