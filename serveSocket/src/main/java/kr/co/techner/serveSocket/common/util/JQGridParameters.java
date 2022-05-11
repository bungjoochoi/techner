package kr.co.techner.serveSocket.common.util;

import java.util.List;

public class JQGridParameters {

	//{"groupOp":"AND","rules":[{"field":"classCodeName","op":"eq","data":""}]}
	private String groupOp;
	private List<Rules> rules;

	public String getGroupOp() {
		return groupOp;
	}


	public void setGroupOp(String groupOp) {
		this.groupOp = groupOp;
	}


	public List<Rules> getRules() {
		return rules;
	}


	public void setRules(List<Rules> rules) {
		this.rules = rules;
	}

	public static class Rules{
		private String field;
		private String op;
		private String data;

		public String getField() {
			return field;
		}
		public void setField(String field) {
			this.field = field;
		}
		public String getOp() {
			return op;
		}
		public void setOp(String op) {
			this.op = op;
		}
		public String getData() {
			return data;
		}
		public void setData(String data) {
			this.data = data;
		}
		
	}

}
