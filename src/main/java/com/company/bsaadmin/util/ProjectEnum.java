package com.company.bsaadmin.util;

import lombok.Getter;

public class ProjectEnum {

	public enum AttendanceStatus  implements EnumValue{
		PRESENT(1L, "Present"),
		ABSENT(2L, "Absent"),
		HALF_DAY(3L, "Half Day");


		private final Long value;
		private final String label;

		AttendanceStatus(Long value, String label) {
			this.value = value;
			this.label = label;
		}

		@Override
		public Long getValue() {
			return value;
		}

		public String getLabel() {
			return label;
		}
	}

	@Getter
	public enum LeaveStatus  implements EnumValue{

		PENDING(1L, "Pending"),
		APPROVED(2L, "Approved"),
		REJECTED(3L, "Rejected"),
		CANCELLED(4L, "Cancelled");

		private final Long value;
		private final String label;

		LeaveStatus(Long value, String label) {
			this.value = value;
			this.label = label;
		}

		@Override
		public Long getValue() {
			return value;
		}

		public String getLabel() {
			return label;
		}

	}
}
