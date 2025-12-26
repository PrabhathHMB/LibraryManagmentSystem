export interface Book {
    id?: string; // Optional because new books don't have an ID yet
    title: string;
    author: string;
    genre: string;
    publicationYear: number;
    shelfLocation: string; // Matches your Java Model instead of 'Copies'
}
