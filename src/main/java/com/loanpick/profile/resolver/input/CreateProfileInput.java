package com.loanpick.profile.resolver.input;

import java.time.LocalDate;

import com.loanpick.profile.entity.enums.CreditGradeStatus;
import com.loanpick.profile.entity.enums.EmploymentForm;
import com.loanpick.profile.entity.enums.EmploymentStatus;
import com.loanpick.profile.entity.enums.LoanProductUsageStatus;
import com.loanpick.profile.entity.enums.ProfileColor;
import com.loanpick.profile.entity.enums.PurposeOfLoan;
import com.loanpick.profile.service.dto.CreateProfileDto;
import com.loanpick.user.entity.User;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

//@formatter:off
@Builder
public record CreateProfileInput(
    @NotNull(message = "직업을 선택해 주세요.")
    EmploymentStatus employmentStatus,
    String workplaceName,
    EmploymentForm employmentForm,
    Integer income,
    LocalDate employmentDate,
    @NotNull(message = "대출 목적을 선택해 주세요.")
    PurposeOfLoan purposeOfLoan,
    @NotNull(message = "대출 희망 금액을 적어주세요.")
    @Min(value = 0, message = "대출 희망 금액은 0원 이상 작성해주세요.")
    Integer desiredLoanAmount,
    @NotNull(message = "이용하는 서비스가 있는지 선택해주세요.")
    LoanProductUsageStatus loanProductUsageStatus,
    @NotNull(message = "현재 가입하신 대출 개수를 적어주세요.")
    @Min(value = 0, message = "대출 개수는 0개 이상 작성해주세요.")
    Integer loanProductUsageCount,
    @NotNull(message = "현재 가입하신 모든 대출 금액을 적어주세요.")
    @Min(value = 0, message = "대출 금액은 0원 이상 작성해주세요.")
    Integer totalLoanUsageAmount,
    Integer creditScore,
    CreditGradeStatus creditGradeStatus,
    @NotBlank(message = "작성하신 프로필의 이름을 만들어주세요.")
    @Size(min = 1, max = 14, message = "프로필 이름은 1자 이상 14자 이하로 작성해주세요.")
    String profileName,
    ProfileColor profileColor
) {
    public CreateProfileDto toDto(User user) {
        return CreateProfileDto.builder()
            .workplaceName(workplaceName)
            .employmentForm(employmentForm)
            .income(income)
            .employmentDate(employmentDate)
            .loanProductUsageStatus(loanProductUsageStatus)
            .user(user)
            .loanProductUsageCount(loanProductUsageCount)
            .totalLoanUsageAmount(totalLoanUsageAmount)
            .purposeOfLoan(purposeOfLoan)
            .desiredLoanAmount(desiredLoanAmount)
            .creditGradeStatus(creditGradeStatus)
            .creditScore(creditScore)
            .profileName(profileName)
            .employmentStatus(employmentStatus)
            .profileColor(profileColor)
            .build();
    }
}
