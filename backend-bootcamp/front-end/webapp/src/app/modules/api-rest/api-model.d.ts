/* tslint:disable */
/* eslint-disable */

export interface AbstractMasterdataDto<T> extends MasterDataInterface<T>, Serializable {
}

export interface ApiErrorDto {
    errors: string[];
    message: string;
}

export interface ApiErrorMessageDto {
    args: { [index: string]: any };
    code: string;
    text: string;
}

export interface ApplicationVersionDto {
    version: string;
}

export interface DateDto {
    day: number;
    month: number;
    year: number;
}

export interface DateTimeDto {
    date: DateDto;
    time: TimeDto;
}

export interface MasterDataDto extends AbstractMasterdataDto<number> {
    id: number;
}

export interface MasterDataInterface<T> {
    description: string;
    id: T;
}

export interface PublicInfoDto {
    features: AppFeature[];
    flavor: string;
}

export interface Serializable {
}

export interface TimeDto {
    hours: number;
    minutes: number;
    seconds?: number;
}

export const enum AppFeature {
    EMPTY = "EMPTY",
}
